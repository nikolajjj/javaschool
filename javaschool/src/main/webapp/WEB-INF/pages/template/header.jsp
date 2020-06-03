<%@include file="includes.jsp"%>
<html>
<head>
    <title>Cargo Transportation company</title>
    <link href="<c:url value="/res/css/components.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/res/css/main.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/res/css/tables.css"/>" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/res/js/angular/angular.js"></script>
    <script type="text/javascript" src="/res/js/angular/OrderController.js"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC7W36kGOadrQ9Vf6WLblP41YZ8pEiFaco<%--&sensor=false--%>" type="text/javascript"></script>
    <script type="text/javascript" src="/res/js/angular/script.js"></script>
    <script type="text/javascript" src="/res/js/angular/wagon.js"></script>
    <script src="<c:url value="/res/js/angular/CityController.js" />"></script>
</head>
<body>
<header class="brand-header">
    <div class="brandbar brandbar-minimized">
        <div class="container-fixed">
            <div class="brand-logo">
                <img src="/res/images/deutsche-telekom-logo.svg" alt="Telekom Logo">
                <span class="sr-only">Telekom Logo</span>
            </div>
            <div class="brand-claim">
                <img src="/res/images/brand-claim.svg" alt="Brand Claim">
                <span class="sr-only">Brand Claim</span>
            </div>
        </div>
    </div>
    <nav class="navbar navbar-default">
        <div class="container-fixed">
            <div class="navbar-expanded">
                <div class="brandnavhead" data-spy="brandnav" data-target="#MainMenu">
                    <button type="button" class="btn btn-clean navbar-btn brandnav-control-up" data-close="brandnav" data-target="#MainMenu">
                        <span class="sr-only">Back</span>
                        <span class="icon icon-navigation-left"></span>
                    </button>

                    <button type="button" class="btn btn-clean navbar-btn navbar-right" data-cancel="brandnav" data-target="#MainMenu">
                        <span class="sr-only">Close Navigation</span>
                        <span class="icon icon-cancel"></span>
                    </button>

                    <label class="navbar-header-label brandnav-label">Home</label>
                </div>
                <div class="navbar-portalname">
                    Cargo Transportation</br>company
                </div>
                <div class="brandnav brandnav-lvl-1" id="MainMenu">
                    <ul class="nav">
                        <c:if test="${pageContext.request.isUserInRole('EMPLOYEE')}">
                            <li><a href="/employee">EmployeeEE</a></li>
                        </c:if>
                        <c:if test="${pageContext.request.isUserInRole('DRIVER')}">
                            <li><a href="/driver">Driver</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
            <div class="navbar-persistent">
                <button type="button" class="btn btn-clean navbar-btn navbar-toggle" data-open="brandnav" data-target="#MainMenu">
                    <span class="sr-only">Open Navigation</span>
                    <span class="icon icon-list-view"></span>
                </button>
                <ul class="nav navbar-nav navbar-nav-icons navbar-right">
                    <c:url value="/logout" var="logoutUrl" />
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <li><a href="javascript:document.getElementById('logout').submit()">Logout</a></li>
                    </c:if>
                    <form id="logout" action="${logoutUrl}" method="post" >
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </ul>
                <ul class="nav navbar-nav navbar-nav-icons navbar-right">
                    <c:url value="/login" var="loginUrl" />
                    <c:if test="${pageContext.request.userPrincipal.name eq null}">
                        <li><a href="javascript:document.getElementById('login').submit()">Login</a></li>
                    </c:if>
                    <form id="login" action="${loginUrl}" method="get" >
                    </form>
                </ul>
            </div>
        </div>
    </nav>
</header>
<body>