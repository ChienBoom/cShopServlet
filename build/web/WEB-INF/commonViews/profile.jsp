<%-- 
    Document   : profile
    Created on : Jul 15, 2024, 11:54:35 AM
    Author     : MinhChien
--%>
<%-- 
    Document   : home
    Created on : Jul 7, 2024, 9:18:47 PM
    Author     : MinhChien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông tin tài khoản</title>
    </head>
    <style>
        .profile-image {
            width: 400px;
            height: 400px;
            border-radius: 50%;
            object-fit: cover;
            margin: 0 auto;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <body class="d-flex flex-column min-vh-100">
        <jsp:include page="_header.jsp"></jsp:include>
            <main class="flex-fill" style="padding-top: 56px; background-color: #f5f5f5">
                <div class="container" style="margin-top: 50px">
                    <div class="row">
                        <div class="col-4 d-flex flex-column align-items-center">
                            <img src="assets/uploads/danhmuc-tainghe.jpg" alt="Profile Image" class="profile-image border border-primary">
                            <button type="button" class="btn btn-primary" style="width: 300px; margin-top:20px">Thay đổi ảnh đại diện</button>
                        </div>
                        <div class="col-8 d-flex flex-column align-items-center justify-content-start" >
                            <div class="d-flex align-items-center">
                                <label for="username" class="fw-medium" style="width: 150px;">Tên tài khoản</label>
                                <input class="border rounded" style="height: 40px; min-width: 250px" type="text" id="username" name="username" value="${User.username}" readonly="true">
                        </div>
                        <div class="d-flex align-items-center" style="margin-top:10px;">
                            <label for="fullname" class="fw-medium" style="width: 150px;">Tên đầy đủ</label>
                            <input class="border rounded" style="height: 40px; min-width: 250px" type="text" id="fullname" name="fullname" value="${User.fullName}" readonly="true">
                        </div>
                        <div class="d-flex align-items-center" style="margin-top:10px;">
                            <label for="email" class="fw-medium" style="width: 150px;">Email</label>
                            <input class="border rounded" style="height: 40px; min-width: 250px" type="text" id="email" name="email" value="${User.email}" readonly="true">
                        </div>
                        <div class="d-flex align-items-center" style="margin-top:10px;">
                            <label for="dOB" class="fw-medium" style="width: 150px;">Ngày sinh</label>
                            <input class="border rounded" style="height: 40px; min-width: 250px" type="date" id="dOB" name="dOB" value="${User.DOB}" readonly="true">
                        </div>
                        <div class="d-flex align-items-center" style="margin-top:10px;">
                            <label for="sex" class="fw-medium" style="width: 150px;">Giới tính</label>
                            <input class="border rounded" style="height: 40px; min-width: 250px" type="text" id="sex" name="sex" value="${User.sex}" readonly="true">
                        </div>
                        <div class="d-flex align-items-center" style="margin-top:10px;">
                            <label for="address" class="fw-medium" style="width: 150px;">Địa chỉ</label>
                            <input class="border rounded" style="height: 40px; min-width: 250px" type="text" id="address" name="address" value="${User.address}" readonly="true">
                        </div>

                        <button type="button" class="btn btn-primary" style="width: 300px; margin-top:20px">Thay đổi thông tin</button>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="_footer.jsp"></jsp:include>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>

</html>
