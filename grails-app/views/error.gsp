<!doctype html>
<html>
<head>
    <title>Unexpected Error</title>
    <meta name="layout" content="main">
    <asset:stylesheet src="errors.css"/>
</head>
<body>
<div class="container--centered">
    <g:if env="development">
        <g:if test="${Throwable.isInstance(exception)}">
            <g:renderException exception="${exception}" />
        </g:if>
        <g:elseif test="${request.getAttribute('javax.servlet.error.exception')}">
            <g:renderException exception="${request.getAttribute('javax.servlet.error.exception')}" />
        </g:elseif>
        <g:else>
            <ul class="errors">
                <li>An error has occurred</li>
                <li>Exception: ${exception}</li>
                <li>Message: ${message}</li>
                <li>Path: ${path}</li>
            </ul>
        </g:else>
    </g:if>
    <g:else>
        <h1>An error has occurred</h1>
    </g:else>
</div>
</body>
</html>