<!doctype html>
<html lang="en">
<head>
    <!-- build:css(.) styles/vendor.css -->
    <!-- bower:css -->
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css" />
    <!-- endbower -->
    <!-- endbuild -->


    <!-- build:js(.) scripts/vendor.js -->
    <!-- bower:js -->
    <script src="bower_components/jquery/dist/jquery.js"></script>
    <script src="bower_components/angular/angular.js"></script>
    <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
    <script src="bower_components/angular-strap/dist/angular-strap.min.js"></script>
    <script src="bower_components/angular-strap/dist/angular-strap.tpl.min.js"></script>
    <script src="bower_components/angular-mocks/angular-mocks.js"></script>
    <!-- endbower -->
    <!-- endbuild -->

    <!-- build:js({.tmp,app}) scripts/scripts.js -->
    <script src="scripts/security/http-auth-interceptor.js"></script>
    <script src="scripts/security/authorization.js"></script>
    <script src="scripts/app.js"></script>
    <!-- endbuild -->

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
