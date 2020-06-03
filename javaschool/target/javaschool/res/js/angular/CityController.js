var app = angular.module("cityApp", []);

app.controller('CityController', function ($scope, $http) {
    getCitiesList();
    // For wagons/drivers update
    var cityId = parseInt($('#city').val());
    $scope.selectedCity = cityId

    function getCitiesList() {
        $http({
            method: 'GET',
            url: '/cities/get/list'
        }).then(function successCallBack(response) {
            $scope.cities = response.data;
            console.log($scope.cities);
        }, function errorCallBack(response) {
            console.log(response.status);
        });
    }
});

compare = function (a, b) {
    let comparison = 0;
    if (a.id > b.id) {
        comparison = 1;
    } else {
        comparison = -1;
    }
    return comparison;
}