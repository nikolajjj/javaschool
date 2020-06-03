<%@include file="../../template/header.jsp" %>
<div class="container-fixed">
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
                <div class="content-list">
                    <a class="content-list-item content-list-item-brand" href="/employee/wagon/list">
                        Wagon
                    </a>
                    <a class="content-list-item content-list-item-brand" href="/employee/drivers">
                        Drivers
                    </a>
                    <a class="content-list-item content-list-item-brand" href="/employee/cargo/list">
                        Cargoes
                    </a>
                    <a class="content-list-item content-list-item-brand" href="/employee/order">
                        Orders
                    </a>
                </div>
            </div>
            <div class="col-l-10" id="content">
                <div class="col-l-8">
                    <form method="get" action="/employee/cargo/add">
                        <input type="submit" value="Add new cargo"/>
                    </form>
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
                                <td>
                                    <div class="popoverHover">
                                        <a>${cargoList.order.order_number}</a>
                                    </div>
                                    <div class="container my-popover-content">
                                        Status: ${cargoList.order.status}<br/>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../../template/footer.jsp" %>
