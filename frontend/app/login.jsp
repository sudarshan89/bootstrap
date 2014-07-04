<!doctype html>
<html lang="en">
<head>
<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css" />
<script type="text/javascript" src="bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<title>Bootstrap</title>
<body>
<div class="modal fade" tabindex="-1" id="form" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form name="form" role="form" method="post" action="login">
            <div class="modal-content">
                <div class="modal-header">
                    <label>Login</label>
                </div>
                <div class="modal-body">
                    <div class="row">
					<c:if test="${empty param.login_error}">
                <div class="alert alert-danger" style="margin-bottom:10px;padding:5px">
                        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </div>
            </c:if>
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
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Login</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(
            function () {
                $('#form').modal({keyboard:false});
            });
</script>
</body>
</html>
