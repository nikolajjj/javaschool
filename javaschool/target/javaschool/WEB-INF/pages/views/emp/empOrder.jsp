<%@ include file="../../template/header.jsp" %>
<div class="container-fixed" ng-app="menu">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-s-6">
                    <h2>Mange cargoes</h2>
                </div>
                <%--<div class="col-s-6" style="float:left;">
                    <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal">
                        <i class="material-icons"></i> <span>Add New Driver</span>
                    </a>
                </div>--%>
            </div>
        </div>
        <div class="row">
            <div class="col-l-2">
                <div class="content-list" ng-controller="menuCtrl">
                    <a class="content-list-item content-list-item-brand" href="/employee/wagon/list">
                        Wagon
                    </a>
                    <a class="content-list-item content-list-item-brand" href="/employee/drivers">
                        Drivers
                    </a>
                    <a class="content-list-item content-list-item-brand" href="/employee/cargo/list">
                        Cargos
                    </a>
                    <a class="content-list-item content-list-item-brand" href="/employee/order">
                        Orders
                    </a>
                </div>
            </div>
            <div class="col-l-10" id="content">
                <form method="get" action="/employee/order/add">
                    <input type="submit" value="Add new order"/>
                </form>
                <table class="table table-hover">
                    <tr>
                        <th>Order number</th>
                        <th>Status</th>
                        <th>Wagon</th>
                    </tr>
                    <c:forEach var="order" items="${orderList}" varStatus="i">
                        <tr>
                            <td>${order.order_number}</td>
                            <td>${order.status}</td>
                            <td>${order.wagon.car_plate}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../template/footer.jsp" %>
