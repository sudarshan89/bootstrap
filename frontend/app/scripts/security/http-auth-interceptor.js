angular.module('http-auth-interceptor',[])
    .factory('authService', ['$http','$rootScope', function($http,$rootScope) {
        return {
            currentUser : undefined,
            login: function(data) {
                var request = $http.post('auth/login', data);
                return request.then(function (response) {
                    this.currentUser = response.data.currentUser;
                    if(!this.currentUser){
                        $rootScope.$broadcast('auth-invalid-credentials',response);
                    }
                    return response;
                });
            },
            requestCurrentUser: function () {
                return $http.post('auth/current-user').then(function (response) {
                    this.currentUser = response.data.currentUser;
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
                    if (rejection.status === 401 && !rejection.config.ignoreAuthModule) {
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

