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
        <h5 class="card-header">Signup</h5>
        <div class="card-body">
            <g:form controller="users" action="save">
                <div class="mb-3">
                    <label for="firstName" class="form-label">First name</label>
                    <input name="firstName" type="text" class="form-control" id="firstName" required/>
                </div>
                <div class="mb-3">
                    <label for="lastName" class="form-label">Last name</label>
                    <input name="lastName" type="text" class="form-control" id="lastName" required/>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email address</label>
                    <input name="email" type="email" class="form-control" id="email" required/>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input name="password" type="password" class="form-control" id="password" required/>
                </div>
                <div class="mb-3">
                    <label for="passwordRepeated" class="form-label">Repeat Password</label>
                    <input name="passwordRepeated" type="password" class="form-control" id="passwordRepeated" required/>
                </div>
                <g:submitButton name="submit" value="Submit" class="btn btn-primary"/>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>
