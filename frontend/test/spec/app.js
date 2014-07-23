'use strict';
describe('frontend',function(){
    var $scope,$modal,$document,$rootScope;
    beforeEach(module('frontend'));
    beforeEach(module('../views/login/login.html'));
    beforeEach(inject(function(_$rootScope_, $controller,_$modal_,_$document_) {
        $document = _$document_;
        $modal = _$modal_;
        $rootScope = _$rootScope_;
        $scope = $rootScope.$new();
        $controller('loginCtrl', {$scope: $scope,$modal:$modal});
    }));
    it("should call openLogin function when user clicks testDrive button",function(){
        $scope.openLogin();
        $rootScope.$digest();
        var element = $document.find('body > div.modal');
        expect(element.length).toBe(1);
    });
    afterEach(function(){
        var modal =  $document.find('body > div.modal');
        if(modal){
            modal.remove();
        }
    });
});