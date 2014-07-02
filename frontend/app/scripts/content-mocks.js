(function() {
    'use strict';
    /**
     * This module is used to simulate backend server for this demo application.
     */
    angular.module('content-mocks',['ngMockE2E'])
        .run(function($httpBackend,$location) {
            $httpBackend.whenPOST('auth/login').respond(function(method, url, data) {
                var user = angular.fromJson(data);
                if(user.name==='s' && user.password ==='s'){
                   window.location.replace('main.html#/admin');
                    return [200,{'currentUser' : 'admin'}];
                }else{
                    return [200,{'error':'invalidPassword'}];
                }
            });
            $httpBackend.whenPOST('auth/current-user').respond(function(method, url, data) {
                return [200,{'currentUser' : 'admin'}];
            });

            $httpBackend.whenPOST('auth/logout').respond(function(method, url, data) {
                return [200];
            });
            $httpBackend.whenGET(/.*/).passThrough();
        });
})();