<html>
<body>
<link href="/js/bootstrap/bootstrap.min.css" rel="stylesheet"/>
<link href="/css/bootstrap/bootstrap-table.css" rel="stylesheet"/>
<script type="text/javascript" src="/js/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap/bootstrap.min.js" ></script>
<script type="text/javascript" src="/js/bootstrap/bootstrap-table.js" ></script>
<script type="text/javascript">
    $(function () {

    });

</script>
<div class="content-wrapper container">
    <div class="col-md-3">

    </div>
    <div class="col-sm-6">
        <div class="panel panel-default">
            <div class="panel-body">
                <form action="/user/save" method="post"  id="userForm" class="form-horizontal">
                    <fieldset>
                        <legend align="center">ADD AN USER</legend>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">First Name</label>
                            <div class="col-sm-8">
                                <input type="text" name="firstName" id="name" class="form-control" />
                            </div>

                        </div>
                    </fieldset>
                    <fieldset>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Last Name</label>
                            <div class="col-sm-8">
                                <input type="text" name="lastName" id="lname" class="form-control" />
                            </div>
                        </div>
                    </fieldset>

                    <fieldset>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">User Name</label>
                            <div class="col-sm-8">
                                <input type="text" name="username" id="username" class="form-control" />
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Password</label>
                            <div class="col-sm-8">
                                <input type="text" name="password" id="pass" class="form-control" />
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <div class="form-group" align="center">
                            <button type="submit" id="createButton"style="width: 50%" class="btn btn-primary btn-lg">CREATE</button>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-3">

    </div>
</div>

</body>
</html>