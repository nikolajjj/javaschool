<%@ include file="../template/header.jsp"%>
<form:form method="post" action="/employee/order/add" modelAttribute="order" ng-app="orderCargoList">
    <form:hidden path="id"/>
    <label for="order_number">Order number</label></br>
    <form:input id="order_number" type="nubmer" path="order_number"/></br>
    <label for="cargo_id">Cargo Id</label></br>
    <div name="selectCargo" ng-controller="orderCargoListCtrl">
        <select name="cargo_id" id="cargo_id" ng-model="selectedCargo" ng-options="y.id for (x, y) in cargoList">
            <%--<option value=""></option>
            <c:forEach items="${cargoList}" var="cargoOption">
                <option value="${cargoOption.id}">${cargoOption.id}</option>
            </c:forEach>--%>
        </select>
        <div>
            <h3>You selected cargo with id: {{selectedCargo.id}}</h3>
            <p>Description: {{selectedCargo.description}}</p>
            <p>Weight: {{selectedCargo.weight}}</p>
            <p>Status: {{selectedCargo.cargoStatus}}</p>
            <p>City from: {{selectedCargo.city_from.name}}</p>
            <p>City from: {{selectedCargo.city_to.name}}</p>
        </div>
        <button ng-click="">Add to order?</button>
    </div>
    <label for="wagon_id">Wagon Id</label>
    <div name="selectWagon">
        <select name="wagon_id" id="wagon_id">
            <option value=""></option>
            <c:forEach items="${wagonList}" var="wagonOption">
                <option value="${wagonOption.id}">${wagonOption.id}</option>
            </c:forEach>
        </select>
    </div>
    <input type="submit" value="Add">
</form:form>
<%@ include file="../template/footer.jsp"%>