'use strict';
angular.module('entrancebook.directives', ['mgcrea.ngStrap.modal','mgcrea.ngStrap.alert'])
.directive('loginWindow',['$modal','$alert',function($modal,$alert){
        return{
            restrict: 'A',
            link: function(scope) {
                var modal = $modal({ template: '../views/login/login.html',show:false});
                var alert;
                scope.$on('auth-invalid-user', function(event,invalidStatus) {
                    modal.$promise.then(modal.show);
                    alert =  $alert({content: invalidStatus, type: 'danger', show: true,container:'#invalid-credential'});
                });
            }
        };
    }]);