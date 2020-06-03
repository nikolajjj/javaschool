<%@ include file="../../template/header.jsp" %>
<div ng-app="cityApp" ng-controller="CityController">
    <form:form method="post" modelAttribute="driver" action="/employee/driver/add">
        <form:hidden path="id"/>
        <div>
            <label for="first_name">First name</label></br>
            <form:input path="first_name" id="first_name" type="text" maxlength="40" required="true"/>
        </div>
        <div>
            <label for="second_name">Last name</label></br>
            <form:input path="second_name" id="second_name" type="text" maxlength="40" required="true"/>
        </div>
        <div>
            <label for="cityId">Current ciy</label><br/>
            <select name="cityId" id="cityId" ng-model="selectedCity"
                    ng-options="city.id as city.name for city in cities" required="true"/>
        </div><br/>
        <input type="submit" value="Add driver"/>
    </form:form>
</div>
<%@ include file="../../template/footer.jsp" %>
