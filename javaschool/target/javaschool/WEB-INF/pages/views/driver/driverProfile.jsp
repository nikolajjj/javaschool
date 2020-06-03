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
            <h1>Driver ${driver.personal_number}, ${driver.first_name} ${driver.second_name}.</h1>
            <c:choose>
                <c:when test="${ order.status ne 'DONE' and not empty order}">
                    <p>Wagon car plate: ${wagon.car_plate}</p>
                    <p>Order id: ${order.id}</p>
                    <p>
                    <c:forEach items="${cargoList}" var="cargo">
                        <p>Cargo#${cargo.id}: from: ${cargo.city_from}, to: ${cargo.city_to}</p></br>
                    </c:forEach>
                    </p>
                    <c:if test="${not empty drivers}">
                        <p>Co-Drivers: <c:forEach items="${drivers}" var="coDriver">${coDriver.personal_number}</c:forEach></p>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <h3>No orders were found</h3>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@ include file="../../template/footer.jsp"%>