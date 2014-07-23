'use strict';

/*
angular.module('mocks',['ngMockE2E'])
    .service('mockSecurity',['$httpBackend',
        function($httpBackend){
            var backEnds ={};
            backEnds.login = function(){
                $httpBackend.whenPOST('1api/login').respond(function(method,url,data) {
                    console.log('********************');
                    if(data.name==='s' && data.password ==='s'){
                        console.log('========================');
                        window.location.replace('main.html#/homepage');
                        return [200,{'currentUser' : 'admin'}];
                    }else{
                        return [200,{'error':'invalidPassword'}];
                    }
                });
            };
            backEnds.currentUser = function(){
                $httpBackend.whenGET('api/security/current-user').respond(function(method,url,data) {
                    return [200,{'data' : {'permissions':'["ROLE_ADMIN1"]'}}];
                });
            };
            return backEnds;
        }]);*/
