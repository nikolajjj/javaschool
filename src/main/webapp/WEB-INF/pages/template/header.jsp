<%@include file="includes.jsp"%>
<html>
<head>
    <title>Cargo Transportation company</title>
    <link href="<c:url value="/res/css/components.min.css"/>" rel="stylesheet" type="text/css">
<%--    <script src="<c:url value="/res/js/angular.js" />"></script>--%>
    <script src="/res/js/angular/angular.js"></script>
    <script src="<c:url value="/res/js/angular/orderCargoList.js" />"></script>
</head>
<body>
<header class="brand-header">
    <div class="brandbar">
        <%--<div class="brandbar-minimized">--%>
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
                <div class="brandnav brandnav-lvl-1" id="MainMenu">
                    <ul class="nav">
                        <li class="active"><a href="#">Client</a></li>
                        <li><a href="/employee">Employee</a></li>
                        <li><a href="/driver">Driver</a></li>
                    </ul>
                </div>
            </div>
            <div class="navbar-persistent">
                <button type="button" class="btn btn-clean navbar-btn navbar-toggle" data-open="brandnav" data-target="#MainMenu">
                    <span class="sr-only">Open Navigation</span>
                    <span class="icon icon-list-view"></span>
                </button>
                <ul class="nav navbar-nav navbar-nav-icons navbar-right">
                    <li><a href="#"><span class="sr-only">Search</span><span class="icon icon-search"></span></a></li>
                    <li><a href="#"><span class="sr-only">Shoppingcart</span><span class="icon icon-shopping-cart"></span></a></li>
                    <li><a href="#"><span class="sr-only">My Profile</span><span class="icon icon-my-profile"></span></a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<body>