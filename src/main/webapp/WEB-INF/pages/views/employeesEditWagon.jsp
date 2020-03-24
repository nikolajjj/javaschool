<%@ include file="../template/header.jsp"%>
<form:form method="post" modelAttribute="wagon"  action="/employee/wagon/update=${wagonId}">
        <form:hidden path="id"/><br/>
        Car plate <form:input path="car_plate"/></br>
        Capacity <form:input path="capacity"/></br>
        Condition <form:input path="state"/></br>
        <select name="city_id">
                <option value=""></option>
                <c:forEach var="city" items="${cityList}">
                        <option value="${city.id}">${city.name}</option>
                </c:forEach>
        </select>
        <%--<select name="current_city">
                <c:forEach items="cityList" var="city">
                        <option value="${city.id}">${city.name}</option>
                </c:forEach>
<%--        </select>--%>
        <input type="submit" value="Update wagon"/>
</form:form>
<%@ include file="../template/footer.jsp"%>