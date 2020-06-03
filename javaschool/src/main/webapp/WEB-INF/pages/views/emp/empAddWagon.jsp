<%@ include file="../../template/header.jsp" %>
<div ng-app="cityApp" ng-controller="CityController">
    <form:form method="post" action="/employee/wagon/add" modelAttribute="wagon">
        <form:hidden path="id"></form:hidden>
        <div>
            <label for="car_plate">Car plate</label></br>
            <form:input path="car_plate" id="car_plate" type="text" maxlength="7" minlength="7" required="true"/>
        </div>
        <div>
            <label for="driver_shift_count">Driver shift count</label></br>
            <form:input path="driver_shift_count" type="number" min="0" max="8" required="true"/>
        </div>
        <div>
            <label for="capacity">Capacity</label></br>
            <form:input path="capacity" id="capacity" type="number" min="0" required="true"/>
        </div>
        <div>
            <label for="state">State</label></br>
            <form:select path="state" id="state" required="true">
                <form:option value=""/>
                <form:option value="ENABLE"/>
                <form:option value="DISABLE"/>
            </form:select>
        </div>
        <div>
            <label for="cityId">Current city</label><br/>
            <select name="cityId" id="cityId" ng-model="selectedCity"
                    ng-options="city.id as city.name for city in cities" required>
                <option value="">---Choose current wagon city---</option>
            </select>
        </div>
        <div>
            <input type="submit" value="Add wagon">
        </div>
    </form:form>
</div>
<%@ include file="../../template/footer.jsp" %>
