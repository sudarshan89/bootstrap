'use strict';

/**
 * @ngdoc overview
 * @name seedApp
 * @description
 * # seedApp
 *
 * Main module of the application.
 */
angular.module('frontend',['mgcrea.ngStrap','http-auth-interceptor'])
    .controller('loginCtrl',['$scope','$modal',function($scope,$modal){
        var modal;
        $scope.openLogin = function(){
            modal =  $modal({scope:$scope,template: 'views/login/login.html',show:true});
        };
    }]);
