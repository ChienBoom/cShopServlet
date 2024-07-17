<%-- 
    Document   : memberManagement
    Created on : Jul 8, 2024, 3:47:25 PM
    Author     : MinhChien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quản lý người dùng</title>
    </head>
    <!--<link rel="stylesheet" href="assets/css/main.css">-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <body class="d-flex flex-column min-vh-100">
        <jsp:include page="_header.jsp"></jsp:include>
            <div class="flex-fill" style="padding-top: 56px; background-color: #f5f5f5">
            <c:if test="${STATUS == 'SUCCESS'}">
                <div class="alert alert-primary position-fixed top-1 end-0 p-3" role="alert" style="width: 400px">
                    <div class="d-flex justify-content-between">
                        ${MESSAGE}
                        <button type="button" clas class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </div>
            </c:if>
            <c:if test="${STATUS == 'ERROR'}">
                <div class="alert alert-danger position-fixed top-1 end-0 p-3" role="alert" style="width: 400px">
                    <div class="d-flex justify-content-between">
                        ${MESSAGE}
                        <button type="button" clas class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </div>
            </c:if>

            <main class="container" style="margin-top: 50px">
                <span class="text-primary" style="font-weight: bold;">QUẢN LÝ NGƯỜI DÙNG</span>
                <table class="table border" style="margin-top: 20px">
                    <thead> 
                        <tr>
                            <th scope="col" class="bg-primary text-white">STT</th>
                            <th scope="col" class="bg-primary text-white">Tên tài khoản</th>
                            <th scope="col" class="bg-primary text-white">Tên đầy đủ</th>
                            <th scope="col" class="bg-primary text-white">Email</th>
                            <th scope="col" class="bg-primary text-white">Ngày sinh</th>
                            <th scope="col" class="bg-primary text-white">Giới tính</th>
                            <th scope="col" class="bg-primary text-white">Địa chỉ</th>
                            <th scope="col" class="bg-primary text-white"><span class="d-flex justify-content-center">Thao tác</span></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${Users}" varStatus="loop">
                            <tr class="align-item-center">
                                <th scope="row">${loop.index + 1}</th>
                                <td>${item.username}</td>
                                <td>${item.fullName}</td>
                                <td>${item.email}</td>
                                <td>${item.DOB}</td>
                                <td>${item.sex}</td>
                                <td>${item.address}</td>
                                <td class="d-flex justify-content-center gap-1">
                                    <c:if test="${item.isActive}">
                                        <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Khoá" data-bs-toggle="modal"
                                                data-bs-target="#BanModal" onclick="BanMember('${item.accountId}', '${item.username}')">
                                            <i class="fa-solid fa-circle-xmark"></i>
                                        </button>
                                        <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Bỏ khoá" disabled>
                                            <i class="fa-solid fa-circle-check"></i>
                                        </button>
                                    </c:if>
                                    <c:if test="${!item.isActive}">
                                        <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Khoá" disabled>
                                            <i class="fa-solid fa-circle-xmark"></i>
                                        </button>
                                        <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Bỏ khoá" 
                                                data-bs-toggle="modal" data-bs-target="#UnBanModal" onclick="UnBanMember('${item.accountId}', '${item.username}')">
                                            <i class="fa-solid fa-circle-check"></i>
                                        </button>
                                    </c:if>
                                    <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Sửa" data-bs-toggle="modal" data-bs-target="#EditModal" disabled="true">
                                        <i class="fa-solid fa-pen-to-square"></i>
                                    </button>
                                    <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Xoá" 
                                            data-bs-toggle="modal" data-bs-target="#DeleteModal" onclick="DeleteMember('${item.accountId}', '${item.username}')">
                                        <i class="fa-solid fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </main>
        </div>

        <!-- Ban Modal -->
        <div class="modal fade" id="BanModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="memberManagement" method="post">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">THÔNG BÁO</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div>Bạn có chắc chắn muốn khoá tài khoản <span id="ban-modal-body"></span></div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="banAccountId" name="banAccountId" value="" hidden="">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="action" name="action" value="BAN" hidden="">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                            <button type="submit" class="btn btn-primary">Đồng ý</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Un Ban Modal -->
        <div class="modal fade" id="UnBanModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="memberManagement" method="post">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">THÔNG BÁO</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                             Bạn có chắc chắn muốn mở khoá tài khoản <span id="unBan-modal-body"></span>
                            <div class="form-group">
                                <input type="text" class="form-control" id="unBanAccountId" name="unBanAccountId" value="" hidden="">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="action" name="action" value="UN-BAN" hidden="">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                            <button type="submit" class="btn btn-primary">Đồng ý</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Edit Modal -->
        <div class="modal fade" id="EditModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">SỬA THÔNG TIN TÀI KHOẢN</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Bạn có chắc chắn muốn mở khoá tài khoản <span id="modal-body"></span>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                        <button type="button" class="btn btn-primary">Lưu</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Delete Modal -->
        <div class="modal fade" id="DeleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="memberManagement" method="post">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">THÔNG BÁO</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                             Bạn có chắc chắn muốn xoá tài khoản <span id="delete-modal-body"></span>
                            <div class="form-group">
                                <input type="text" class="form-control" id="deleteAccountId" name="deleteAccountId" value="" hidden="">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="action" name="action" value="DELETE" hidden="">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                            <button type="submit" class="btn btn-primary">Đồng ý</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="_footer.jsp"></jsp:include>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script>
//                                                    debugger;
                                                    $(document).ready(function () {
                                                        $('[data-toggle="tooltip"]').tooltip();
                                                    });

                                                    function BanMember(accountId, username) {
                                                        document.getElementById('ban-modal-body').textContent = username;
                                                        document.getElementById('banAccountId').value = accountId;
                                                    }
                                                    
                                                    function UnBanMember(accountId, username) {
                                                        document.getElementById('unBan-modal-body').textContent = username;
                                                        document.getElementById('unBanAccountId').value = accountId;
                                                    }
                                                    
                                                    function DeleteMember(accountId, username) {
                                                        document.getElementById('delete-modal-body').textContent = username;
                                                        document.getElementById('deleteAccountId').value = accountId;
                                                    }
        </script>
    </body>
</html>
