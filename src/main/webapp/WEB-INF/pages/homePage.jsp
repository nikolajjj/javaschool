<%@include file="template/header.jsp"%>
<h1>Home page</h1>
<h1>Hello ${pageContext.request.userPrincipal.name} ${pageContext.request.userPrincipal.role}</h1>
<c:if test="${not empty pageContext.request.userPrincipal}">
    <p>asdasdad</p>
    <c:if test="${pageContext.request.isUserInRole('USER')}">

        User ${pageContext.request.userPrincipal.name} in ADMIN Group

    </c:if>

</c:if>
<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>
<%@include file="template/footer.jsp"%>