<%-- 
    Document   : home
    Created on : Jul 7, 2024, 9:18:47 PM
    Author     : MinhChien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/x-icon" href="assets/logo/logo.jpg">
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
                <c:if test="${STATUS == 'SUCCESS'}">
                <div class="alert alert-primary position-fixed top-1 end-0 p-3" role="alert" style="width: 400px">
                    <div class="d-flex justify-content-between">
                        ${MESSAGE}
                        <button type="button" clas class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </div>
            </c:if>
            <c:if test="${STATUS == 'ERROR'}">
                <div class="alert alert-primary position-fixed top-1 end-0 p-3" role="alert" style="width: 400px">
                    <div class="d-flex class="alert alert-danger position-fixed top-1 end-0 p-3" role="alert" style="width: 400px">
                        <div class="d-flex justify-content-between">
                            ${MESSAGE}
                            <button type="button" clas class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </div>
            </c:if>
            <div class="container" style="margin-top: 50px">
                <div class="row">
                    <div class="col-4 d-flex flex-column align-items-center">
                        <img src="assets/uploads/${User.pictureUrl}" alt="Profile Image" class="profile-image border border-primary">
                        <button type="button" class="btn btn-primary" style="width: 300px; margin-top:20px" 
                                data-bs-toggle="modal" data-bs-target="#ChangeAvatarModal" onclick="changeAvatar('${User.username}')">Thay đổi ảnh đại diện</button>
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

                        <button type="button" class="btn btn-primary" style="width: 300px; margin-top:20px"
                                data-bs-toggle="modal" data-bs-target="#EditModal" onclick="editProfile('${User.username}', '${User.fullName}',
                                                '${User.email}', '${User.DOB}', '${User.sex}', '${User.address}')">Thay đổi thông tin</button>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="_footer.jsp"></jsp:include>

        <!-- Change Avatar Modal -->
        <div class="modal fade" id="ChangeAvatarModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="profile" method="post" enctype="multipart/form-data">
                        <div class="modal-header bg-primary">
                            <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THAY ĐỔI ẢNH ĐẠI DIỆN</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" class="form-control" id="action" name="action" value="CHANGE-AVATAR" hidden="true">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="changeUsername" name="changeUsername" hidden="true">
                            </div>
                            <div class="row">
                                <div class="col-12 form-group">
                                    <label for="changePicture">Hình ảnh</label>
                                    <input type="file" class="form-control" id="changePicture" name="changePicture" required="true">
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                            <button type="submit" class="btn btn-primary">Lưu</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Edit Modal -->
        <div class="modal fade" id="EditModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="profile" method="post" enctype="multipart/form-data">
                        <div class="modal-header bg-primary">
                            <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">CẬP NHẬT THÔNG TIN CÁ NHÂN</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" class="form-control" id="action" name="action" value="EDIT" hidden="true">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="editUsername" name="editUsername" hidden="true">
                            </div>
                            <div class="row">
                                <div class="col-6 form-group">
                                    <label for="editFullName">Tên đầy đủ</label>
                                    <input type="text" class="form-control" id="editFullName" name="editFullName" required="true">
                                </div>
                                <div class="col-6 form-group">
                                    <label for="editEmail">Email</label>
                                    <input type="text" class="form-control" id="editEmail" name="editEmail" required="true">
                                </div>
                                <div class="col-6 form-group">
                                    <label for="editDOB">Ngày sinh</label>
                                    <input type="date" class="form-control" id="editDOB" name="editDOB" required="true">
                                </div>
                                <div class="col-6 form-group">
                                    <label for="editAddress">Địa chỉ</label>
                                    <input type="text" class="form-control" id="editAddress" name="editAddress" required="true">
                                </div>
                                <div class="col-6 form-group">
                                    <label for="editSex">Giới tính</label>
                                    <select class="form-control border rounded" name="editSex" id="editSex">
                                        <option value="NAM">Nam</option>
                                        <option value="NỮ">Nữ</option>
                                        <option value="KHÁC">Khác</option>
                                    </select>
                                </div>
                                <div class="col-12 form-group">
                                    <label for="editUserPicture">Hình ảnh</label>
                                    <input type="file" class="form-control" id="editUserPicture" name="editUserPicture">
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                            <button type="submit" class="btn btn-primary">Lưu</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script>
                                    debugger;
                                    $(document).ready(function () {
                                        $('[data-toggle="tooltip"]').tooltip();
                                    });

                                    setTimeout(function () {
                                        // Select all alerts with the 'alert' class
                                        var alerts = document.querySelectorAll('.alert');
                                        alerts.forEach(function (alert) {
                                            // Hide each alert
                                            $(alert).alert('close');
                                        });
                                    }, 2000);
                                    //
                                    function editProfile(username, fullName, email, dob, sex, address) {
                                        document.getElementById('editUsername').value = username;
                                        document.getElementById('editFullName').value = fullName;
                                        document.getElementById('editEmail').value = email;
                                        document.getElementById('editDOB').value = dob;
                                        document.getElementById('editSex').value = sex;
                                        document.getElementById('editAddress').value = address;
                                    }

                                    function changeAvatar(username) {
                                        document.getElementById('changeUsername').value = username;
                                    }
        </script>
    </body>

</html>
