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
                            <h3>Thông tin người dùng</h3>
                        </div>
                        <div class="card-body">
                            <form action="userNotifi" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="INSERT" hidden="true">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="insUsername" name="insUsername" value="${USERNAME}" hidden="true">
                                </div>
                                <div class="row">
                                    <div class="col-6 form-group">
                                        <label for="insFullName">Tên đầy đủ</label>
                                        <input type="text" class="form-control" id="insFullName" name="insFullName" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="insEmail">Email</label>
                                        <input type="text" class="form-control" id="insEmail" name="insEmail" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="insDOB">Ngày sinh</label>
                                        <input type="date" class="form-control" id="insDOB" name="insDOB" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="insAddress">Địa chỉ</label>
                                        <input type="text" class="form-control" id="insAddress" name="insAddress" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="insSex">Giới tính</label>
                                        <select class="form-control border rounded" name="insSex" id="insSex">
                                            <option value="NAM">Nam</option>
                                            <option value="NỮ">Nữ</option>
                                            <option value="KHÁC">Khác</option>
                                        </select>
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="insUserPicture">Hình ảnh</label>
                                        <input type="file" class="form-control" id="insUserPicture" name="insUserPicture" required="">
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block mt-2">Cập Nhật</button>

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
