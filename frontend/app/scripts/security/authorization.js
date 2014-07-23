'use strict';
angular.module('security.authorization',['http-auth-interceptor'])
    .factory('securityAuthorization',
    ['authService','$rootScope','$q', function(authService,$rootScope,$q) {
        return {
            requireAuthenticatedUser : function (userInfo) {
                var deffered = $q.defer();
                authService.requestCurrentUser().then(function (response) {
                    if (userInfo === response.data.permissions) {
                        deffered.resolve();
                    }else{
                        deffered.reject();
                        $rootScope.$broadcast('auth-invalid-user',"Unauthorized user");
                    }
                });
                return deffered.promise;
            }
        };
    }]);