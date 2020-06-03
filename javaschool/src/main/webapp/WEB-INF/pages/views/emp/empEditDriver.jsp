<%@ include file="../../template/header.jsp"%>
<form:form method="post" modelAttribute="driver"  action="/employee/driver/update=${driverId}">
    <form:hidden path="id"/><br/>
    <div>
        <label for="first_name">First name</label></br>
        <form:input path="first_name" id="first_name" type="text" maxlength="40" required="true"/>
    </div>
    <div>
        <label for="second_name">Last name</label></br>
        <form:input path="second_name" id="second_name" type="text" maxlength="40" required="true"/>
    </div>
    <input type="submit" value="Update driver"/>
</form:form>
<%@ include file="../../template/footer.jsp"%>