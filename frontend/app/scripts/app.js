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
    .controller('loginCtrl',['$scope','$modal','authService','$alert',function($scope,$modal,authService,$alert){
        var modal = undefined;
        var alert = undefined;
        $scope.openLogin = function(){
            modal =  $modal({scope:$scope,template: '../views/login/login.html',show:true});
        };
        $scope.login = function(user){
            if(alert){
                alert.hide();
            }
            authService.login(user);
        }
        $scope.$on('auth-invalid-credentials',function(){
            alert=  $alert({content: 'Invalid username or password', type: 'danger', show: true,container:'#invalid-credential'});
        })
    }]);
