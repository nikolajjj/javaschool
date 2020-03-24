<%@include file="../template/header.jsp"%>
<div class="container-fixed" ng-app="menu">
    <h2 class="underline">EMPLOYEE MENU</h2>
    <div class="row">
        <div class="col-l-2">
            <div class="content-list" ng-controller="menuCtrl">
                <a class="content-list-item content-list-item-brand" href="/employee/wagon/list">
                    Wagon
                </a>
                <a class="content-list-item content-list-item-brand" href="/employee/drivers">
                    Drivers
                </a>
                <a class="content-list-item content-list-item-brand" href="/employee/cargoes">
                    Cargos
                </a>
                <a class="content-list-item content-list-item-brand" href="/employee/order">
                    Orders
                </a>
            </div>
        </div>
        <div class="col-l-8" id="content">
            <div class="col-l-8">
                <table class="table table-hover">
                    <tr>
                        <th>Id</th>
                        <th>Description</th>
                        <th>Weight</th>
                        <th>Status</th>
                        <th>City from</th>
                        <th>City to</th>
                        <th>Order id</th>
                    </tr>
                    <c:forEach var="cargoList" items="${cargoList}" varStatus="i">
                        <tr>
                            <td>${cargoList.id}</td>
                            <td>${cargoList.description}</td>
                            <td>${cargoList.weight}</td>
                            <td>${cargoList.status}</td>
                            <td>${cargoList.city_from.name}</td>
                            <td>${cargoList.city_to.name}</td>
                            <td>${cargoList.order.id}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div>
            <a href="/employee/cargo/add">Add</a>
        </div>
    </div>
</div>
<%@include file="../template/footer.jsp"%>
