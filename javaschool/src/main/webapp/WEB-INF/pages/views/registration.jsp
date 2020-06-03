<%@include file="../template/includes.jsp" %>
<%@include file="../template/header.jsp"%>
<form:form modelAttribute="user" action="/registration" method="post" name="registerForm" id="registerForm">
    <form:hidden path="id"/>
    <label for="username">Username:</label>
    <form:input path="username" type="text" placeholder="Username" id="username" required="required"/></br>
    <label for="password">Password:</label>
    <form:input path="password" type="password" placeholder="Password" id="password" required="required"/></br>
    <input type="submit" value="Register"/>
</form:form>
<%@include file="../template/footer.jsp"%>