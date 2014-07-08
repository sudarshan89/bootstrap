<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css" />
    <script type="text/javascript" src="bower_components/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<title>Bootstrap</title>
<body>
<div class="container" style="margin-top: 50px">
    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">Why you are here?</h3>
                </div>
                <div class="panel-body">
                   You are In this page since you have entered a wrong credentials in the login screen.
                    <br>
                    Try entering correct login username and password to go to home page.
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <form name="form" role="form" method="post" action="login">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Login</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-10 col-md-offset-2">
                                <c:if test="${empty param.login_error}">
                                    <div class="alert alert-danger" style="margin-bottom:10px;padding:5px">
                                        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-md-12">
                                <div id="invalid-credential"></div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">User Name</label>
                                    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Name" name="j_username">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Password</label>
                                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="j_password">
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="panel-footer"><button type="submit" class="btn btn-primary">Login</button></div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
