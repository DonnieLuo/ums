<html>
    <head>
        <title>Login Page</title>
    </head>
    <link href="/js/bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <body>
    <div class="container" style="width: 50%">

        <form action="/login" method="post" class="form-signin">
            <h2 class="form-signin-heading">Please sign in</h2>
            <label class="sr-only">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Username" required="required" autofocus="autofocus" />
            <label class="sr-only">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Password" required="required" />
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"/> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>

    </div>

    </body>
</html>