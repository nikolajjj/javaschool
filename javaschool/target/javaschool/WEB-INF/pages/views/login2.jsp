<%@include file="../template/includes.jsp" %>
<%@include file="../template/header.jsp"%>
<c:if test="${not empty error}"><div>${error}</div></c:if>
<c:if test="${not empty message}"><div>${message}</div></c:if>
<form action="/login" method="post">
    <label for="username">Username</label>
    <input type="text" placeholder="Username" id="username" name="username" required/><br/>
    <label for="password">Password</label>
    <input type="password" placeholder="Password" id="password" name="password" required/></br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="submit" value="Sign in"/>
</form>
<%@include file="../template/footer.jsp"%>