angular.module('security.authorization',['http-auth-interceptor'])
    .factory('securityAuthorization',
        ['authService','$rootScope', function(authService,$rootScope) {
        return {
            isAuthenticated : function () {
                return !!authService.currentUser;
            },
            requireAuthenticatedUser : function (userInfo) {
                 authService.requestCurrentUser().then(function (response) {
                    if (userInfo !== response.data.currentUser) {
                        $rootScope.$broadcast('auth-invalid-user',userInfo);
                    }
                });
            }
        };
    }]);