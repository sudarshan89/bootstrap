'use strict';

/**
 * @ngdoc function
 * @name seedApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the seedApp
 */
angular.module("app-name",['ngRoute','http-auth-interceptor','entrancebook.directives'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('homepage',
            {templateUrl: '../views/dashboard.html', controller: 'MainCtrl'
            }
        )
    }])
    .controller('MainCtrl', function ($scope,$modal) {

    })

