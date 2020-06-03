<%@ include file="../../template/header.jsp"%>
<div class="row" ng-app="orderCargoList" ng-controller="orderCargoListCtrl">
    <div class="col-l-4">
        <div name="selectCargo">
            <select name="cargo_id" id="cargo_id" ng-model="selectedCargo" ng-options="y.id for (x, y) in cargoList">
            </select>
            <div>
                <table class="table-small">
                    <caption>Cargo information</caption>
                    <tr>
                        <th>Id</th>
                        <th>Description</th>
                        <th>Weight</th>
                        <th>Status</th>
                        <th>City from</th>
                        <th>City to</th>
                    </tr>
                    <tr>
                        <td>{{selectedCargo.id}}</td>
                        <td>{{selectedCargo.description}}</td>
                        <td>{{selectedCargo.weight}}</td>
                        <td>{{selectedCargo.cargoStatus}}</td>
                        <td>{{selectedCargo.city_from.name}}</td>
                        <td>{{selectedCargo.city_to.name}}</td>
                    </tr>
                </table>
            </div>
            <button type="button" ng-click="updateAssignedCargoList(selectedCargo)">Add cargo to order</button>
        </div>
        <div name="selectWagon">
            <label for="wagon_id">Wagon Id</label>
            <select name="wagon_id" id="wagon_id" ng-model="selectedWagon" ng-options="y.id for (x, y) in possiblesWagon" ng-change="updateDriverList(selectedWagon)">
            </select>
<%--            <button ng-click="updateDriverList(selectedWagon)">Set wagon</button>--%>
        </div>
        <div name="selectDriver">
            <label for="driver_id">Driver Id</label>
            <select name="driver_id" id="driver_id" ng-model="selectedDriver" ng-options="y.id for (x, y) in possiblesDrivers">
            </select>
            <div>
                <table class="table-small">
                    <caption>Driver information</caption>
                    <tr>
                        <th>Id</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Personal number</th>
                        <th>Status</th>
                        <th>Hours worked this month</th>
                        <th>Current city</th>
                    </tr>
                    <tr>
                        <td>{{selectedDriver.id}}</td>
                        <td>{{selectedDriver.first_name}}</td>
                        <td>{{selectedDriver.second_name}}</td>
                        <td>{{selectedDriver.personal_number}}</td>
                        <td>{{selectedDriver.status}}</td>
                        <td>{{selectedDriver.hoursWorkedThisMonth}}</td>
                        <td>{{selectedDriver.current_city.name}}</td>
                    </tr>
                </table>
            </div>
            <button type="button" ng-click="updateAssignedDriverList(selectedDriver)">Assign driver to order</button>
        </div>
        <button ng-click="createOrder()">Create order</button>
    </div>
    <div class="col-l-6">
        <div>
            <table class="table table-hover">
                <caption>Assigned cargoes</caption>
                <tbody>
                    <tr>
                        <th>Id</th>
                        <th>Description</th>
                        <th>Weight</th>
                        <th>Status</th>
                        <th>City from</th>
                        <th>City to</th>
                    </tr>
                    <tr ng-repeat="cargo in assignedCargoList">
                        <td>{{cargo.id}}</td>
                        <td>{{cargo.description}}</td>
                        <td>{{cargo.weight}}</td>
                        <td>{{cargo.cargoStatus}}</td>
                        <td>{{cargo.city_from.name}}</td>
                        <td>{{cargo.city_to.name}}</td>
                        <td ng-click="deleteAssignedCargo(cargo)">Delete cargo</td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="6" class="numeric text-brand text-semibold">Final order weight: {{summaryWeight}}</td>
                    </tr>
                </tfoot>
            </table>
        </div>
        <div>
            <table class="table table-hover">
                <caption>Assigned drivers</caption>
                <tbody>
                    <tr>
                        <th>Id</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Personal number</th>
                        <th>Status</th>
                        <th>Current city</th>
                    </tr>
                    <tr ng-repeat="driver in assignedDriverList">
                        <td>{{driver.id}}</td>
                        <td>{{driver.first_name}}</td>
                        <td>{{driver.second_name}}</td>
                        <td>{{driver.personal_number}}</td>
                        <td>{{driver.status}}</td>
                        <td>{{driver.current_city.name}}</td>
                        <td ng-click="deleteAssignedDriver(driver)">Delete driver</td>
                    </tr>
                </tbody>
                <tfoot>
                <!-- some extra result information -->
                </tfoot>
            </table>
        </div>
        <div>
            <table class="table table-responsive">
                <caption>Assigned wagon</caption>
                <tr>
                    <th>Car plate</th>
                    <td>{{selectedWagon.car_plate}}</td>
                </tr>
                <tr>
                    <th>Capacity</th>
                    <td>{{selectedWagon.capacity}}</td>
                </tr>
                <tr>
                    <th>State</th>
                    <td>{{selectedWagon.wagonStatus}}</td>
                </tr>
                <tr>
                    <th>Current city</th>
                    <td>{{selectedWagon.current_city.name}}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<%@ include file="../../template/footer.jsp"%>