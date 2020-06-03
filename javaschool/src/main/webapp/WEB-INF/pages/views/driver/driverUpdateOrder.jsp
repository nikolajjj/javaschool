<%@ include file="../../template/header.jsp"%>
<div class="container-fixed">
    <h2 class="underline">DRIVERS MENU</h2>
    <div class="row">
        <div class="col-l-2">
            <div class="content-list">
                <a class="content-list-item content-list-item-brand" href="/driver/profile">
                    General information
                </a>
                <a class="content-list-item content-list-item-brand" href="/driver/order">
                    Order information
                </a>
            </div>
        </div>
        <div class="col-l-10">
            <c:choose>
                <c:when test="${ order.status ne 'DONE' and not empty order}">
                    <c:choose>
                        <c:when test="${not empty driverShift.begin and not empty driverShift.end or empty driverShift.begin and empty driverShift.end}">
                            <form method="post" action="/driver/order/beginDriverShift">
                                <input type="submit" value="Begin shift"/>
                            </form>
                        </c:when>
                        <c:otherwise><p>Current Status: ${driver.status}</p>
                            <form method="post" action="/driver/order/changeDriverStatus">
                                <input type="hidden" name="driver_id" value="${driver.id}"/>
                                <select name="driver_status"required>
                                    <option value="" >Choose status</option>
                                    <option value="DRIVING">Driving</option>
                                    <option value="SECOND_DRIVER">Second driver</option>
                                    <option value="LOAD_UNLOAD_CARGO">Load/unload cargo</option>
                                    <option value="REST_IN_SHIFT">Rest in shift</option>
                                </select>
                                <input type="submit" value="Change status">
                            </form>
                            <table class="table table-small">
                                <c:forEach items="${cargoList}" var="cargo">
                                    <tr>
                                        <td>${cargo.id}</td>
                                        <td>${cargo.description}</td>
                                        <td>${cargo.city_to}</td>
                                        <td>${cargo.city_to}</td>
                                        <td>${cargo.status}</td>
                                        <td>
                                            <form method="post" action="/driver/order/changeCargoStatus">
                                                <input type="hidden" name="cargo_id" value="${cargo.id}"/>
                                                <select name="cargo_status" required="true">
                                                    <option value="">Choose status</option>
                                                    <option value="SHIPPED">Shipped</option>
                                                    <option value="DELIVERED">Delivered</option>
                                                </select>
                                                <input type="submit" value="Change cargo status"/>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <form method="post" action="/driver/order/endDriverShift">
                                <input type="submit" value="End shift"/>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <h3>No orders were found</h3>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@ include file="../../template/footer.jsp"%>