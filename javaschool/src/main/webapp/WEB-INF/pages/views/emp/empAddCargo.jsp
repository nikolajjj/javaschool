<%@ include file="../../template/header.jsp" %>
<div ng-app="cityApp" ng-controller="CityController">
    <form:form method="post" action="/employee/cargo/add" modelAttribute="cargo">
        <form:hidden path="id"></form:hidden>
        <div>
            <label for="description">Description</label></br>
            <form:input path="description" id="description" type="text" maxlength="80" required="true"/>
        </div>
        <div>
            <label for="weight">Weight</label></br>
            <form:input path="weight" id="weight" type="number" min="0" required="true"/>
        </div>
        <div>
            <label for="from">City from</label>
            <select name="from" id="from" ng-model="from"
                    ng-options="from.id as from.name for from in cities" required>
                <option value="">---Choose city from---</option>
            </select>
        </div>
        <div>
            <label for="to">City to</label>
            <select name="to" id="to" ng-model="to"
                    ng-options="to.id as to.name for to in cities" required>
                <option value="">---Choose city to---</option>
            </select>
        </div>
        <div>
            <input type="submit" value="Create cargo">
        </div>
    </form:form>
</div>
<%@ include file="../../template/footer.jsp" %>
