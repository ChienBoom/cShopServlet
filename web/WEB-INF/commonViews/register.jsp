<%-- 
    Document   : register
    Created on : Jul 8, 2024, 11:40:07 AM
    Author     : MinhChien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/x-icon" href="assets/logo/logo.jpg">
        <title>Đăng ký</title>
    </head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card mt-5">
                        <div class="card-header text-center">
                            <h3>Đăng Ký</h3>
                        </div>
                        <div class="card-body">
                            <form action="register" method="post" onSubmit="return validateRegister()">
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
                                <div class="d-flex align-items-center justify-content-between">
                                    <button type="submit" class="btn btn-primary btn-block mt-2">Đăng Ký</button>
                                    <a href="${pageContext.request.contextPath}/login">
                                        Đã có tài khoản. Đi đăng nhập
                                    </a>
                                </div>


                            </form>
                            <% if (request.getAttribute("STATUS") == "USERNAME-EXIS") {%>
                            <div class="alert alert-danger mt-3" role="alert">
                                <%= request.getAttribute("MESSAGE")%>
                            </div>
                            <% }%>
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

                                function validateRegister() {
                                    var password = document.getElementById('password').value;
                                    if (!validatePassword(password))return false
                                    return true;
                                }

                                function validatePassword(password) {
                                    const minLength = 8;
                                    const hasUpperCase = /[A-Z]/.test(password);
                                    const hasLowerCase = /[a-z]/.test(password);
                                    const hasNumber = /[0-9]/.test(password);
                                    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);

                                    if (password.length < minLength) {
                                        alert('Mật khẩu phải có ít nhất 8 ký tự.');
                                        return false;
                                    }
                                    if (!hasUpperCase) {
                                        alert('Mật khẩu phải chứa ít nhất một chữ cái viết hoa.');
                                        return false;
                                    }
                                    if (!hasLowerCase) {
                                        alert('Mật khẩu phải chứa ít nhất một chữ cái viết thường.');
                                        return false;
                                    }
                                    if (!hasNumber) {
                                        alert('MMật khẩu phải chứa ít nhất một chữ số.');
                                        return false;
                                    }
                                    if (!hasSpecialChar) {
                                        alert('Mật khẩu phải chứa ít nhất một ký tự đặc biệt.');
                                        return false;
                                    }

                                    return true;
                                }
        </script>
    </body>
</html>
