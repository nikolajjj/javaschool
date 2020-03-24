var app = angular.module('orderCargoList', []);

app.controller('orderCargoListCtrl', function($scope, $http) {
    /*$scope.cargoList = function() {
        $http.get('/employee/order/add').success(function() {
            alert("here cargo");
        });
    }*/
    getCargoList();
    /*$scope.cars = {
        car01 : {brand : "Ford", model : "Mustang", color : "red"},
        car02 : {brand : "Fiat", model : "500", color : "white"},
        car03 : {brand : "Volvo", model : "XC90", color : "black"}
    }*/
    function getCargoList() {
        $http({
            method : 'GET',
            url : '/employee/order/cargolist'
        }).then(function successCallBack(response) {
            console.log("success");
            $scope.cargoList = response.data;
            console.log("$scope.cargoList: ", $scope.cargoList)
        }, function errorCallBack(response) {
            console.log("fail");
            console.log(response.status);
        });
    }
});