'use strict';
describe('directives',function(){
    beforeEach(module('entrancebook.directives'));
    describe('login window',function(){
        var child;
        var directiveElement,$compile,$rootScope,$document;
        beforeEach(module('../views/login/login.html'));

        beforeEach(inject(function(_$compile_, _$rootScope_,_$document_){
            $compile = _$compile_;
            $rootScope = _$rootScope_;
            child = $rootScope.$new();
            $document = _$document_;
            directiveElement = $compile("<div login-window></div>")($rootScope);
        }));
        it("should open modal window on 'auth-invalid-user' event",function(){
            $rootScope.$broadcast('auth-invalid-user',"Unauthorized user1");
            $rootScope.$digest();
            var Modalelement = $document.find('body > div.modal');
            expect(Modalelement.length).toBe(1);
        });
        it("should alert with proper msg on 'auth-invalid-user' event",function(){
            $rootScope.$broadcast('auth-invalid-user',"Unauthorized user1");
            $rootScope.$digest();
            var AlertElement = $('#invalid-credential').text();
            expect(AlertElement).toContain('Unauthorized user');
        });
        afterEach(function(){
            var ele =  $document.find('body > div.modal');
            if(ele){
                ele.remove();
            }
        });
    });
});
