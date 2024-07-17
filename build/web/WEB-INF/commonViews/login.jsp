<%-- 
    Document   : login
    Created on : Jul 8, 2024, 11:29:17 AM
    Author     : MinhChien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
    </head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card mt-5">
                        <div class="card-header text-center">
                            <h3>Đăng Nhập</h3>
                        </div>
                        <div class="card-body">
                            <form action="login" method="post">
                                <div class="form-group">
                                    <label for="username">Tên tài khoản</label>
                                    <input type="text" class="form-control" id="username" name="username" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Mật khẩu</label>
                                    <input type="password" class="form-control" id="password" name="password" required>
                                </div>
                                <div>
                                    <input type="checkbox" id="showPassword" >
                                    <span>Hiển thị mật khẩu</span>
                                </div>
                                <a href="${pageContext.request.contextPath}/forgotPassword">
                                    Quên mật khẩu
                                </a>
                                <div class="d-flex align-items-center justify-content-between">
                                    <button type="submit" class="btn btn-primary btn-block mt-2">Đăng nhập</button>
                                    <a href="${pageContext.request.contextPath}/register">
                                        Chưa có tài khoản. Đi đăng ký
                                    </a>
                                </div>

                            </form>
                            <% if (request.getAttribute("STATUS") == "ERROR") {%>
                            <div class="alert alert-danger mt-3" role="alert">
                                <%= request.getAttribute("MESSAGE")%>
                            </div>
                            <% }%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const showPasswordCheckbox = document.getElementById('showPassword');
                const passwordInput = document.getElementById('password');

                showPasswordCheckbox.addEventListener('change', function () {
                    if (showPasswordCheckbox.checked) {
                        passwordInput.type = 'text';
                    } else {
                        passwordInput.type = 'password';
                    }
                });
            });
        </script>
    </body>
</html>
