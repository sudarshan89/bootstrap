angular.module('entrancebook.directives', ['mgcrea.ngStrap'])
.directive('loginWindow',['$modal',function($modal){
        return{
            restrict: 'A',
            link: function(scope, elem, attrs) {
                var modal = $modal({ template: '../views/login/login.html',show:false});
                scope.$on('auth-invalid-user', function() {
                    modal.$promise.then(modal.show);
                });
            }
        }
    }]);