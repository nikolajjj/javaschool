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
                    <th>Personal numbber</th>
                    <th>First name</th>
                    <th>Second name</th>
                    <th>Status</th>
                    <th>Current city</th>
                    <th>Current wagon</th>
                </tr>
                <c:forEach var="driverItem" items="${driverList}" varStatus="i">
                    <tr>
                        <td>${driverItem.id}</td>
                        <td>${driverItem.personal_number}</td>
                        <td>${driverItem.first_name}</td>
                        <td>${driverItem.second_name}</td>
                        <td>${driverItem.status}</td>
                        <td>${driverItem.current_city.name}</td>
                        <td>${driverItem.current_wagon.id}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<%@ include file="../template/footer.jsp"%>