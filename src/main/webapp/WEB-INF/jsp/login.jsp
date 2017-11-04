<!DOCTYPE html>
<html lang="en-us" xmlns:html="http://www.w3.org/1999/html">
<head>
    <title title="Login Page"></title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">

    <link rel="stylesheet" href="vendor/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="vendor/css/bootstrap/bootstrap-theme.min.css">




</head>

<body>
<div class="container">
<div class="page-header">
    <h2>Login</h2>
</div>

<div class="row">

    <div class="col-md-6">
        <form name="loginForm" action="/login" method="post" class="form-horizontal" novalidate>
            <div class="form-group">
                <div class="col-sm-9">
                    <input id="username" name="username" type="email" class="form-control" required placeholder="Email" focus="true" value="user1@xxx.com"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-9">
                    <input id="password" name="password" type="password" class="form-control" required  placeholder="Password" value="user1Pass"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-9 col-sm-offset-3">
                    <input type="submit" value="Log In" class="btn btn-primary"/>
                </div>
            </div>
        </form>
    </div>

    <div class="col-md-6">
        <p class="alert alert-info">Login with either user1@xxx.com:user1Pass  or "admin1@xxx.com:admin1Pass .</p>
    </div>

</div>
</div>

<script src="vendor/js/jquery/jquery.min.js"></script>
<script src="vendor/js/bootstrap/bootstrap.min.js"></script>
</body>

</html>