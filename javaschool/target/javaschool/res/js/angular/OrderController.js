var app = angular.module('orderCargoList', []);

app.controller('orderCargoListCtrl', function($scope, $http) {
    getCargoList();
    getWagonList();
    getDriverList();
    $scope.assignedCargoList = [];
    $scope.assignedDriverList = [];
    $scope.summaryWeight = 0;
    $scope.possiblesWagon = [];
    $scope.possiblesDrivers = [];
    const MAXIMUM_HOURS_WORKED = 176;

    function getCargoList() {
        $http({
            method : 'GET',
            url : '/employee/order/cargolist'
        }).then(function successCallBack(response) {
            $scope.cargoList = response.data;
            console.log($scope.cargoList);
        }, function errorCallBack(response) {
            console.log(response.status);
        });
    }

    function getWagonList() {
        $http({
            method : 'GET',
            url : '/employee/wagon/wagonlist'
        }).then(function successCallBack(response) {
            $scope.wagonList = response.data;
            console.log($scope.wagonList);
        }, function errorCallBack(response) {
            console.log(response.status);
        });
    }

    function getDriverList() {
        $http({
            method : 'GET',
            url : '/drivers/get/list'
        }).then(function successCallBack(response) {
            $scope.driverList = response.data;
            console.log($scope.driverList);
        }), function errorCallBack(response) {
            console.log(response.status);
        }
    }

    $scope.updateAssignedCargoList = function (selectedCargo) {
        if(selectedCargo != null) {
            if ($scope.assignedCargoList.length == 0) {
                $scope.assignedCargoList.push(selectedCargo);
                $scope.summaryWeight += selectedCargo.weight;
                var index = $scope.cargoList.indexOf(selectedCargo);
                if (index != -1) {
                    $scope.cargoList.splice(index, 1);
                }
            }
            else if (selectedCargo.city_from.id == $scope.assignedCargoList[$scope.assignedCargoList.length - 1].city_to.id) {
                $scope.assignedCargoList.push(selectedCargo);
                $scope.summaryWeight += $scope.selectedCargo.weight;
                if ($scope.selectedWagon != null) {
                    $scope.weightLeft = $scope.selectedWagon.capacity - $scope.summaryWeight;
                }
                var index = $scope.cargoList.indexOf(selectedCargo);
                if (index != -1) {
                    $scope.cargoList.splice(index, 1);
                }
            }
            $scope.selectedDriver = null;
            $scope.selectedWagon = null;
        }
        $scope.selectedCargo = null;
        updateWagonList();
        $scope.updateDriverList();
    }

    $scope.updateAssignedDriverList = function(selectedDriver) {
        if (selectedDriver != null) {
            $scope.assignedDriverList.push(selectedDriver);
            var index = $scope.possiblesDrivers.indexOf(selectedDriver);
            if (index != -1) {
                $scope.possiblesDrivers.splice(index, 1);
            }
        }
        $scope.selectedDriver = null;
    }

    let updateWagonList = function () {
        $scope.possiblesWagon = [];
        $scope.wagonList.forEach(item => {
            if ($scope.assignedCargoList.length != 0) {
                if ((item.current_city.id == $scope.assignedCargoList[0].city_from.id) && (item.state == "ENABLE") &&
                    isWagonCanCarryTheWeight($scope.assignedCargoList, item)) {
                    $scope.possiblesWagon.push(item);
                }
            }
        });
    }

    let isWagonCanCarryTheWeight = function(cargoList, wagon) {
        let currentWeight = 0;
        for(let i = 0; i < cargoList.length; i++) {
            if(cargoList[i].weight > wagon.capacity) {
                return false;
            }
        }
        return true;
    }

    $scope.updateDriverList = function() {
        $scope.assignedDriverList = [];
        $scope.possiblesDrivers = [];
        $scope.driverList.forEach(item => {
            if ($scope.selectedWagon != null) {
                if (isWorkingHoursGonnaBeOverWithThisOrder(item) && item.current_city.id == $scope.selectedWagon.current_city.id) {
                    $scope.possiblesDrivers.push(item);
                }
            }
        });
    }

    async function isWorkingHoursGonnaBeOverWithThisOrder(item) {
        var HoursWithThisOrder = item.hoursWorkedThisMonth;
        $scope.assignedCargoList.forEach(item => {
            var origin = new google.maps.LatLng(item.city_from.latitude, item.city_from.longitude);
            var destination = item.city_to.latitude + ', ' +  item.city_to.longitude;
            var request = {
                origin: origin,
                destination: destination,
                travelMode: google.maps.DirectionsTravelMode.DRIVING
            }
            HoursWithThisOrder += getDurationRoute(request).then();
        })
        if (HoursWithThisOrder < MAXIMUM_HOURS_WORKED) {
            return true;
        } else {
            return false;
        }
    }

    async function getDurationRoute(request) {
        var directionsService = new google.maps.DirectionsService();
        directionsService.route( request, function(response, status)  {
            if (status == google.maps.DirectionsStatus.OK) {
                var point = response.routes[ 0 ].legs[ 0 ]
                console.log(point.duration.value / 60 / 60 + " - current duration route")
                return point.duration.value / 60 / 60;
            }
        });
    }

$scope.deleteAssignedCargo = function(cargo) {
        var index = $scope.assignedCargoList.indexOf(cargo);
        if(index != -1) {
            $scope.assignedCargoList.splice(index, 1);
        }
        $scope.cargoList.push(cargo);
        $scope.cargoList.sort(compare);
        $scope.selectedWagon = null;
        $scope.assignedDriverList = [];
        $scope.possiblesDrivers = [];
        $scope.summaryWeight -= cargo.weight;
        updateWagonList();
    }

    $scope.deleteAssignedDriver = function(driver) {
        var index = $scope.assignedDriverList.indexOf(driver);
        if (index != -1) {
            $scope.assignedDriverList.splice(index, 1);
        }
        $scope.possiblesDrivers.push(driver);
        $scope.possiblesDrivers.sort(compare);
    }

    compare = function(a, b) {
        let comparison = 0;
        if(a.id > b.id) {
            comparison = 1;
        } else {
            comparison = -1;
        }
        return comparison;
    }

    $scope.createOrder = function() {
        $scope.order = {
            wagon : $scope.selectedWagon,
            cargo : $scope.assignedCargoList,
            driver : $scope.assignedDriverList
        }
        if ($scope.order.wagon == null || $scope.order.cargo.length == 0 || $scope.order.driver.length == 0) {
            alert("order is not fully completed");
            return;
        }
        $http({
            method : 'POST',
            url : '/employee/order/add',
            data : angular.toJson($scope.order),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).then(function successCallBack(response) {
            alert("Order was successfully created!")
        }, function errorCallBack(response) {
            console.log(response.status);
        });
    }
});