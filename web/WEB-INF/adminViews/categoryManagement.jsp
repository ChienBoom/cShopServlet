<%-- 
    Document   : categoryManagement
    Created on : Jul 10, 2024, 2:47:22 PM
    Author     : MinhChien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quản lý danh mục</title>
    </head>
    <!--<link rel="stylesheet" href="assets/css/main.css">-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <body class="d-flex flex-column min-vh-100">
        <jsp:include page="_header.jsp"></jsp:include>
            <div class="flex-fill" style="padding-top: 56px">
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

                <main class="container" style="margin-top: 50px">
                    <div class="d-flex justify-content-between">
                        <span class="text-primary" style="font-weight: bold;">QUẢN LÝ DANH MỤC</span>
                        <div class="d-flex">
                            <input type="text" class="border rounded" style="margin-right: 10px" id="searchCategory" name="searchCategory" value="${searchInput}" placeholder="Nhập tên danh mục">
                            <button type="button" style="margin-right: 10px"  class="btn btn-primary" data-toggle="tooltip" title="Tìm kiếm" 
                                    data-bs-toggle="modal" data-bs-target="#" onclick="searchCategory()">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </button>
                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Thêm mới" data-bs-toggle="modal" data-bs-target="#AddModal">
                                <i class="fa-solid fa-plus"></i>Thêm mới
                            </button>
                        </div>
                    </div>
                    <table class="table border" style="margin-top: 20px">
                        <thead> 
                            <tr>
                                <th scope="col" class="bg-primary text-white">STT</th>
                                <th scope="col" class="bg-primary text-white">Tên danh mục</th>
                                <th scope="col" class="bg-primary text-white">Số lượng sản phẩm</th>
                                <th scope="col" class="bg-primary text-white">Mô tả</th>
                                <th scope="col" class="bg-primary text-white">Hình ảnh</th>
                                <th scope="col" class="bg-primary text-white"><span class="d-flex justify-content-center">Thao tác</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${Categories}" varStatus="loop">
                                <tr class="align-item-center">
                                    <th scope="row">${loop.index + 1}</th>
                                    <td>${item.name}</td>
                                    <td>${item.numberProduct}</td>
                                    <td>${item.description}</td>
                                    <td><img src="assets/uploads/${item.pictureUrl}" class="img-thumbnail" style="width: 100px; height: 100px" alt="..."></td>
                                    <td>
                                        <div class="d-flex justify-content-center gap-1">
                                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Chi tiết sản phẩm của danh mục"
                                                    onclick="showProduct('${item.id}')">
                                                <i class="fa-solid fa-circle-check"></i>
                                            </button>
                                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Sửa"
                                                    data-bs-toggle="modal" data-bs-target="#EditModal" onclick="editCategory('${item.id}', '${item.name}', '${item.numberProduct}', '${item.description}')">
                                                <i class="fa-solid fa-pen-to-square"></i>
                                            </button>
                                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Xoá" 
                                                    data-bs-toggle="modal" data-bs-target="#DeleteModal" onclick="deleteCategory('${item.id}', '${item.name}')">
                                                <i class="fa-solid fa-trash"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </main>
            </div>

            <!-- Show Modal -->
            <div class="modal fade" id="ShowModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form id="showProCateForm" action="productManagement" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="showProCateId" name="showProCateId" value="" hidden="">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="SHOW-PRODUCT-CATEGORY" hidden="">
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

            <!-- Search Modal -->
            <div class="modal fade" id="SearchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form id="searchCateForm" action="categoryManagement" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="searchCategoryInput" name="searchCategoryInput" hidden="">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="SEARCH" hidden="">
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

            <!-- Add Modal -->
            <div class="modal fade" id="AddModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="categoryManagement" method="post" enctype="multipart/form-data">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÊM MỚI DANH MỤC</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="ADD" hidden="true">
                                </div>
                                <div class="row">
                                    <div class="col-6 form-group">
                                        <label for="addCateName">Tên danh mục</label>
                                        <input type="text" class="form-control" id="addCateName" name="addCateName" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="addNumProCate">Số lượng sản phẩm</label>
                                        <input type="number" class="form-control" id="addNumProCate" name="addNumProCate" value="0">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="addPicUrlCate">Hình ảnh</label>
                                        <input type="file" class="form-control" id="addPicUrlCate" name="addPicUrlCate" required="true">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="addDescCate">Mô tả</label>
                                        <textarea rows="4" cols="50" class="form-control" id="addDescCate" name="addDescCate">
                                        </textarea>  
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
                        <form action="categoryManagement" method="post" enctype="multipart/form-data">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">SỬA THÔNG TIN DANH MỤC</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="EDIT" hidden="true">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="editCateId" name="editCateId" hidden="true">
                                </div>
                                <div class="row">
                                    <div class="col-6 form-group">
                                        <label for="addCateName">Tên danh mục</label>
                                        <input type="text" class="form-control" id="editCateName" name="editCateName" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="addNumProCate">Số lượng sản phẩm</label>
                                        <input type="number" class="form-control" id="editNumProCate" name="editNumProCate" value="0">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="addPicUrlCate">Hình ảnh</label>
                                        <input type="file" class="form-control" id="editPicUrlCate" name="editPicUrlCate">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="addDescCate">Mô tả</label>
                                        <textarea rows="4" cols="50" class="form-control" id="editDescCate" name="editDescCate">
                                        </textarea>  
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

            <!-- Delete Modal -->
            <div class="modal fade" id="DeleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="categoryManagement" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Bạn có chắc chắn muốn xoá danh mục <span id="delete-modal-body"></span>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="deleteCategoryId" name="deleteCategoryId" value="" hidden="">
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

                                                        function categoryDetail(accountId, username) {
                                                            document.getElementById('ban-modal-body').textContent = username;
                                                            document.getElementById('banAccountId').value = accountId;
                                                        }

                                                        function editCategory(id, name, numberProduct, description) {
                                                            document.getElementById('editCateId').value = id;
                                                            document.getElementById('editCateName').value = name;
                                                            document.getElementById('editNumProCate').value = numberProduct;
                                                            document.getElementById('editDescCate').value = description;
                                                        }

                                                        function deleteCategory(deleteCategoryId, categoryName) {
                                                            document.getElementById('delete-modal-body').textContent = categoryName;
                                                            document.getElementById('deleteCategoryId').value = deleteCategoryId;
                                                        }

                                                        function showProduct(categoryId) {
                                                            document.getElementById('showProCateId').value = categoryId;
                                                            document.getElementById("showProCateForm").submit();
                                                        }

                                                        function searchCategory() {
                                                            document.getElementById('searchCategoryInput').value = document.getElementById('searchCategory').value;
                                                            document.getElementById("searchCateForm").submit();
                                                        }

            </script>
    </body>
</html>

