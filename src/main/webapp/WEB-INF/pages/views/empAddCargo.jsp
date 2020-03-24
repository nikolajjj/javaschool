<%@ include file="../template/header.jsp"%>
<form:form method="post" action="/employee/cargo/add" modelAttribute="cargo">
    <form:hidden path="id"></form:hidden>
    <label for="description">Description</label></br>
    <div>
<%--        <input type="text" id="description" placeholder="Description" value="${cargo.description}" pa>--%>
        <form:input path="description" id="description" type="text" placeholder="Description"></form:input>
    </div>
    <label for="weight">Weight</label></br>
    <div>
<%--        <input type="number" id="weight" placeholder="weight" value="${cargo.weight}">--%>
        <form:input path="weight"></form:input>
    </div>
    <div>
        <select name="city_from">
            <option value=""></option>
            <c:forEach items="${cities}" var="cityOption">
                <option value="${cityOption.id}">${cityOption.name}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="city_to">
            <option value=""></option>
            <c:forEach items="${cities}" var="cityOption">
                <option value="${cityOption.id}">${cityOption.name}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <input type="submit" value="add">
    </div>
</form:form>
<%@ include file="../template/footer.jsp"%>
