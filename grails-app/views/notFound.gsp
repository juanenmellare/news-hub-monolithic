<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <asset:stylesheet src="errors.css"/>
        <title>Page Not Found</title>
    </head>
    <body>
        <div class="container--centered container-error">
            <h1>Page Not Found</h1>
            <h1>${request.forwardURI}</h1>
        </div>
    </body>
</html>