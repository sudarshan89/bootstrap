'use strict';

/**
 * @ngdoc function
 * @name seedApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the seedApp
 */
angular.module("app-name",['ngRoute','http-auth-interceptor','entrancebook.directives','security.authorization'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/homepage',
            {templateUrl: '../views/dashboard.html', controller: 'MainCtrl',
                resolve:{
                    isAuthenticated: ['securityAuthorization',function(securityAuthorization){
                        return securityAuthorization.requireAuthenticatedUser('["ROLE_ADMIN"]');
                    }]
                }
            }
        );
    }])
    .controller('MainCtrl', function () {
    });

