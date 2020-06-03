var wagon = angular.module('wagon', []);

wagon.controller('wagonCtrl', function($scope, $http) {
    getWagonList();
    $scope.isVisible = false;

    function getWagonList() {
        $http({
            method : 'GET',
            url : '/employee/wagonList'
        }).then(function successCallBack(response) {
            $scope.wagonList = response.data;
            console.log($scope.wagonList)
        }, function errorCallBack(response) {
            console.log(response.status);
        });
    }

    $scope.updateWagon = function(wagon) {
        console.log(wagon.id);
        $scope.IsVisible = $scope.IsVisible ? false : true;
    }
});

$(document).ready(function() {
    $("#click").click(function() {
        $("#editEmployeeModal").modal();
    });
})