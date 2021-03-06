<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
    <g:layoutTitle default="News Hub"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="newspaper.svg"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
          crossorigin="anonymous">
    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark background__ub--primary fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand logo" href="/">
            <asset:image src="newspaper.svg" height="40" class="d-inline-block align-text-top logo__image"/>
            <span class="logo__span">Hub</span>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse navbar__items" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link navbar__items__link" aria-current="page" href="/">News</a>
                </li>
                <g:if test="${session.token}">
                    <li class="nav-item">
                        <g:link class="nav-link navbar__items__link" controller="users" action="logout">
                            Logout
                        </g:link>
                    </li>
                </g:if>
                <g:else>
                    <li class="nav-item">
                        <a class="nav-link navbar__items__link" href="/signIn">Sign In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link navbar__items__link" href="/signup">Sign Up</a>
                    </li>
                </g:else>
            </ul>
        </div>
    </div>
</nav>
<div class="container container-body">
    <g:layoutBody/>
</div>
<footer class="footer background__ub--secondary">
    <span class="footer__text">News Hub | Monolithic</span>
</footer>
<asset:javascript src="application.js"/>
<script src="https://cdn.jsdelivr.net/npm/masonry-layout@4.2.2/dist/masonry.pkgd.min.js"
        integrity="sha384-GNFwBvfVxBkLMJpYMOABq3c+d3KnQxudP/mGPkzpZSTYykLBNsZEnG2D9G/X/+7D"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>