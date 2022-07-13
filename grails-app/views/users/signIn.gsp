<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home</title>
    <asset:stylesheet src="users.css"/>
</head>
<body>
<div class="container--centered">
    <div class="card card--border-primary-red card--narrow">
        <h5 class="card-header">Sign In</h5>
        <div class="card-body">
            <g:form controller="users" action="authenticate">
                <div class="mb-3">
                    <label for="email" class="form-label">Email address</label>
                    <input name="email" type="email" class="form-control" id="email" required/>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input name="password" type="password" class="form-control" id="password" required/>
                </div>
                <g:submitButton name="submit" value="Submit" class="btn btn-primary"/>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>
