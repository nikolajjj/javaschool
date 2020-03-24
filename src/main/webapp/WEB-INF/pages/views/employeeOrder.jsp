<%@ include file="../template/header.jsp"%>
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
                        <th>Order number</th>
                        <th>Status</th>
                        <th>Wagon</th>
                    </tr>
                    <c:forEach var="order" items="${orderList}" varStatus="i">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.order_number}</td>
                            <td>${order.status}</td>
                            <td>${order.wagon.id}</td>
                            <td><form action="/employee/order/update=${order.id}" method="get"><input type="submit" value="Edit"/></form></td>
                            <td><form action="/employee/order/delete=${order.id}" method="post"><input type="submit" value="Delete"/></form></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <a href="/employee/order/add">Add new order</a>
        </div>
    </div>
</div>
<%@ include file="../template/footer.jsp"%>
