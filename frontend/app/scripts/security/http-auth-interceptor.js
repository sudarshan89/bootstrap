'use strict';
angular.module('http-auth-interceptor',[])
    .factory('authService', ['$http','$rootScope','$window', function($http) {
        return {
            requestCurrentUser: function () {
                return $http.get('api/security/current-user').then(function (response) {
                    return response;
                });
            }
        };
    }])

/**
 * $http interceptor.
 * On 401 response (without 'ignoreAuthModule' option) stores the request
 * and broadcasts 'event:angular-auth-loginRequired'.
 */
    .config(['$httpProvider', function($httpProvider) {
        $httpProvider.interceptors.push(['$rootScope', '$q', function($rootScope, $q) {
            return {
                responseError: function(rejection) {
                    if ((rejection.status === 401 || rejection.status === 500) && !rejection.config.ignoreAuthModule) {
                        var deferred = $q.defer();
                        $rootScope.$broadcast('auth-invalid-user', rejection);
                        return deferred.promise;
                    }
                    // otherwise, default behaviour
                    return $q.reject(rejection);
                }
            };
        }]);
    }]);

