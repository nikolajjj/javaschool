<%@ include file="../template/header.jsp"%>
<div class="container-fixed">
    <h2 class="underline">EMPLOYEE MENU</h2>
    <div class="row">
        <div class="col-l-2">
            <div class="content-list">
                <a class="content-list-item content-list-item-brand" href="/employee/wagon/list">
                    Wagons
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
        <div class="col-l-8">
            <table class="table table-hover">
                <tr>
                    <th>Id</th>
                    <th>Car plate</th>
                    <th>Capacity</th>
                    <th>Condition</th>
                    <th>Current city</th>
                </tr>
            <c:forEach var="wagonItem" items="${wagonList}" varStatus="i">
                    <tr>
                        <td>${wagonItem.id}</td>
                        <td>${wagonItem.car_plate}</td>
                        <td>${wagonItem.capacity}</td>
                        <td>${wagonItem.state}</td>
                        <td>${wagonItem.current_city.name}</td>
<%--                        <td><a href="/employee/wagon/update=">Edit</a></td>--%>
                        <td><form action="/employee/wagon/update=${wagonItem.id}" method="get"><input type="submit" value="Edit"/></form></td>
                        <td><form action="/employee/wagon/delete=${wagonItem.id}" method="post"><input type="submit" value="Delete"/></form></td>
                    </tr>
            </c:forEach>
            </table>
        </div>
    </div>
</div>
<%@ include file="../template/footer.jsp"%>