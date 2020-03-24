var controller = angular.module('menu', []);

controller.controller('menuCtrl', function($scope, $http) {
    $scope.addNewWagon = function() {
        $http.get('/employee/wagon/list').success(function() {
        });
    }
});