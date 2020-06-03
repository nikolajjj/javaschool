<%@ include file="../../template/header.jsp"%>
<div class="container-fixed" ng-app="wagon" ng-controller="wagonCtrl">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-s-6">
                    <h2>Mange wagons</h2>
                </div>
                <%--<div class="col-s-6" style="float:left;">
                    <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal">
                        <i class="material-icons"></i> <span>Add New Employee</span>
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
                <form method="get" action="/employee/wagon/add">
                    <input type="submit" value="Add new wagon"/>
                </form>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Car plate</th>
                            <th>Driver shift count</th>
                            <th>Capacity</th>
                            <th>Condition</th>
                            <th>Current city</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${wagonList}" var="wagon">
                        <c:if test="${wagon.state eq 'ENABLE'}">
                            <tr>
                                <td>${wagon.car_plate}</td>
                                <td>${wagon.driver_shift_count}</td>
                                <td>${wagon.capacity}</td>
                                <td>${wagon.state}</td>
                                <td>${wagon.current_city.name}</td>
                                <td>
                                    <form action="/employee/wagon/update=${wagon.id}" method="get">
                                        <button type="submit">Edit</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="/employee/wagon/delete=${wagon.id}" method="post">
                                        <button type="submit>">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div id="editEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Edit Employee</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <textarea class="form-control" required=""></textarea>
                        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <input type="text" class="form-control" required="">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-info" value="Save">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../template/footer.jsp"%>