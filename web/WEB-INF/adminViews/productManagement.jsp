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
        <link rel="icon" type="image/x-icon" href="assets/logo/logo.jpg">
        <title>Quản lý sản phẩm</title>
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
                        <span class="text-primary" style="font-weight: bold;">QUẢN LÝ SẢN PHẨM</span>
                        <div class="d-flex">
                            <select class="border rounded" style="margin-right: 10px; min-width: 150px; max-width: 200px" name="searchCategoryId" id="searchCategoryId">

                                <c:choose>
                                    <c:when test="${999 == searchCategoryId}">
                                        <option value="999" selected="">Tất cả</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value=999>Tất cả</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="item" items="${Categories}" varStatus="loop">
                                    <c:choose>
                                        <c:when test="${item.id == searchCategoryId}">
                                            <option value="${item.id}" selected="">${item.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${item.id}">${item.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <input type="text" class="border rounded" style="margin-right: 10px" id="searchProduct" name="searchProduct" value="${searchInput}" placeholder="Nhập tên sản phẩm">
                            <button type="button" style="margin-right: 10px"  class="btn btn-primary" data-toggle="tooltip" title="Tìm kiếm" 
                                    data-bs-toggle="modal" data-bs-target="#" onclick="searchProduct()">
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
                                <th scope="col" class="bg-primary text-white">Tên sản phẩm</th>
                                <th scope="col" style="min-width: 100px" class="bg-primary text-white">Danh mục</th>
                                <th scope="col" style="min-width: 100px" class="bg-primary text-white">SL bán/còn</th>
                                <th scope="col" class="bg-primary text-white">Mô tả</th>
                                <th scope="col" class="bg-primary text-white">Hình ảnh</th>
                                <th scope="col" class="bg-primary text-white"><span class="d-flex justify-content-center">Thao tác</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${Products}" varStatus="loop">
                                <tr class="align-item-center">
                                    <th scope="row">${loop.index + 1}</th>
                                    <td>${item.name}</td>
                                    <td>${item.category.name}</td>
                                    <td>${item.quantitySold} / ${item.quantityStock}</td>
                                    <td>${item.description}</td>
                                    <td><img src="assets/uploads/${item.pictureUrl}" class="img-thumbnail" style="width: 100px; height: 100px" alt="..."></td>
                                    <td>
                                        <div class="d-flex justify-content-center gap-1">
                                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Chi tiết sản phẩm của danh mục"
                                                    onclick="showDetail('${item.id}')">
                                                <i class="fa-solid fa-circle-check"></i>
                                            </button>
                                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Sửa"
                                                    data-bs-toggle="modal" data-bs-target="#EditModal" 
                                                    onclick="editProduct('${item.id}', '${item.name}', '${item.categoryId}', '${item.quantitySold}', '${item.quantityStock}', '${item.description}')">
                                                <i class="fa-solid fa-pen-to-square"></i>
                                            </button>
                                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Xoá" 
                                                    data-bs-toggle="modal" data-bs-target="#DeleteModal" onclick="deleteProduct('${item.id}', '${item.name}')">
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

            <!-- Search Modal -->
            <div class="modal fade" id="SearchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form id="searchProForm" action="productManagement" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="searchProductInput" name="searchProductInput" hidden="">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="searchCategoryIdInput" name="searchCategoryIdInput" hidden="">
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
            <div class="modal fade" id="AddModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" onSubmit="return validateAddProduct()">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="productManagement" method="post" enctype="multipart/form-data">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÊM MỚI SẢN PHẨM</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="ADD" hidden="true">
                                </div>
                                <div class="row">
                                    <div class="col-6 form-group">
                                        <label for="addProName">Tên sản phẩm</label>
                                        <input type="text" class="form-control" id="addProName" name="addProName" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="addProCateId">Danh mục</label>
                                        <select class="form-control" name="addProCateId" id="addProCateId">
                                            <c:forEach var="item" items="${Categories}" varStatus="loop">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="addProQuanSold">Số lượng đã bán</label>
                                        <input type="number" class="form-control" id="addProQuanSold" name="addProQuanSold" value="0" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="addProQuanStock">Số lượng còn trong kho</label>
                                        <input type="number" class="form-control" id="addProQuanStock" name="addProQuanStock" value="0" required="true">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="addProPicture">Hình ảnh</label>
                                        <input type="file" class="form-control" id="addProPicture" name="addProPicture" required="true">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="addProDesc">Mô tả</label>
                                        <textarea rows="4" cols="50" class="form-control" id="addProDesc" name="addProDesc">
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
            <div class="modal fade" id="EditModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" onSubmit="return validateEditProduct()">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="productManagement" method="post" enctype="multipart/form-data">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÊM MỚI SẢN PHẨM</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="EDIT" hidden="true">
                                    <input type="text" class="form-control" id="editProId" name="editProId" hidden="true">
                                </div>
                                <div class="row">
                                    <div class="col-6 form-group">
                                        <label for="editProName">Tên sản phẩm</label>
                                        <input type="text" class="form-control" id="editProName" name="editProName" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="editProCateId">Danh mục</label>
                                        <select class="form-control" name="editProCateId" id="editProCateId">
                                            <c:forEach var="item" items="${Categories}" varStatus="loop">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="editProQuanSold">Số lượng đã bán</label>
                                        <input type="number" class="form-control" id="editProQuanSold" name="editProQuanSold" value="0" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="editProQuanStock">Số lượng còn trong kho</label>
                                        <input type="number" class="form-control" id="editProQuanStock" name="editProQuanStock" value="0" required="true">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="editProPicture">Hình ảnh</label>
                                        <input type="file" class="form-control" id="editProPicture" name="editProPicture">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="editProDesc">Mô tả</label>
                                        <textarea rows="4" cols="50" class="form-control" id="editProDesc" name="editProDesc">
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
                        <form action="productManagement" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Bạn có chắc chắn muốn xoá sản phẩm <span id="delete-modal-body"></span>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="deleteProId" name="deleteProId" value="" hidden="">
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
            
            <!-- show Product Detail Modal -->
            <div class="modal fade" id="ShowDetailModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form id="ShowDetailForm" action="productDetailManagement" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="showProductId" name="showProductId" hidden="">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="SHOW-PRODUCT-DETAIL" hidden="">
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

                                                        function showDetail(productId) {
                                                            document.getElementById('showProductId').value = productId;
                                                            document.getElementById("ShowDetailForm").submit();
                                                        }
//
                                                        function editProduct(id, name, categoryId, quantitySold, quantityStock, description) {
                                                            document.getElementById('editProId').value = id;
                                                            document.getElementById('editProName').value = name;
                                                            document.getElementById('editProCateId').value = categoryId;
                                                            document.getElementById('editProQuanSold').value = quantitySold;
                                                            document.getElementById('editProQuanStock').value = quantityStock;
                                                            document.getElementById('editProDesc').value = description;
                                                        }

                                                        function deleteProduct(deleteProId, proName) {
                                                            document.getElementById('delete-modal-body').textContent = proName;
                                                            document.getElementById('deleteProId').value = deleteProId;
                                                        }

                                                        function searchProduct() {
                                                            document.getElementById('searchProductInput').value = document.getElementById('searchProduct').value;
                                                            document.getElementById('searchCategoryIdInput').value = document.getElementById('searchCategoryId').value;
                                                            document.getElementById("searchProForm").submit();
                                                        }
                                                        
                                                        function validateAddCategory() {
                                                            var quanSold = document.getElementById('addProQuanSold').value;
                                                            var quanStock = document.getElementById('addProQuanStock').value;
                                                            if(!validateNumber(quanSold, "Vui lòng nhập số lượng sản phẩm đã bán >=0")) return false
                                                            if(!validateNumber(quanStock, "Vui lòng nhập số lượng sản phẩm còn trong kho >=0")) return false
                                                            return true;
                                                        }
                                                        
                                                        function validateEditProduct() {
                                                            var quanSold = document.getElementById('editProQuanSold').value;
                                                            var quanStock = document.getElementById('editProQuanStock').value;
                                                            if(!validateNumber(quanSold, "Vui lòng nhập số lượng sản phẩm đã bán >=0")) return false
                                                            if(!validateNumber(quanStock, "Vui lòng nhập số lượng sản phẩm còn trong kho >=0")) return false
                                                            return true;
                                                        }
                                                        
                                                        function validateNumber(value, message) {
                                                            if (value < 0) {
                                                                alert(message);
                                                                return false;
                                                            }
                                                            return true;
                                                        }
            </script>
    </body>
</html>

