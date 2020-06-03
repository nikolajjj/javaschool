<%@ include file="../../template/header.jsp" %>
<div class="container-fixed">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-s-6">
                    <h2>Mange drivers</h2>
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
                <div class="content-list">
                    <a class="content-list-item content-list-item-brand" href="/employee/wagon/list">
                        Wagons
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
            <div class="col-l-10">
                <form method="get" action="/employee/driver/add">
                    <input type="submit" value="Add new driver"/>
                </form>
                <table class="table table-hover">
                    <tr>
                        <th>Personal number</th>
                        <th>First name</th>
                        <th>Second name</th>
                        <th>Status</th>
                        <th>Current city</th>
                        <th>Current wagon</th>
                    </tr>
                    <c:forEach var="driverItem" items="${driverList}">
                        <c:if test="${driverItem.status ne 'DISABLED'}">
                            <tr>
                                <td>${driverItem.personal_number}</td>
                                <td>${driverItem.first_name}</td>
                                <td>${driverItem.second_name}</td>
                                <td>${driverItem.status}</td>
                                <td>${driverItem.current_city.name}</td>
                                <td>
                                    <div class="popoverHover">
                                        <a>${driverItem.current_wagon.id}</a>
                                    </div>
                                    <div class="container my-popover-content">
                                        Car plate: ${driverItem.current_wagon.car_plate}<br/>
                                        Driver shift: ${driverItem.current_wagon.driver_shift_count}<br/>
                                        State: ${driverItem.current_wagon.state}<br/>
                                        Capacity: ${driverItem.current_wagon.capacity}<br/>
                                    </div>
                                </td>
                                <td>
                                    <form method="get" action="/employee/driver/update=${driverItem.id}">
                                        <input type="submit" value="Update"/>
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="/employee/driver/delete=${driverItem.id}">
                                        <input type="submit" value="Delete"/>
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../template/footer.jsp" %>