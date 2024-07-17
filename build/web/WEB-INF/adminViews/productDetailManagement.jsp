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
        <title>Quản lý chi tiết sản phẩm</title>
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
                        <span class="text-primary" style="font-weight: bold;">QUẢN LÝ CHI TIẾT SẢN PHẨM</span>
                        <div class="d-flex">
                            <select class="border rounded" style="margin-right: 10px; min-width: 150px; max-width: 200px" name="searchProductId" id="searchProductId">
                                <c:choose>
                                    <c:when test="${999 == searchProductId}">
                                        <option value="999" selected="">Tất cả</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value=999>Tất cả</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="item" items="${Products}" varStatus="loop">
                                    <c:choose>
                                        <c:when test="${item.id == searchProductId}">
                                            <option value="${item.id}" selected="">${item.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${item.id}">${item.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <input type="text" class="border rounded" style="margin-right: 10px; min-width: 150px" value="${searchInput}" id="searchProductDetail" name="searchProductDetail" placeholder="Nhập tên sản phẩm">
                            <button type="button" style="margin-right: 10px"  class="btn btn-primary" data-toggle="tooltip" title="Tìm kiếm" 
                                    data-bs-toggle="modal" data-bs-target="#" onclick="searchProductDetail()">
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
                                <th scope="col" class="bg-primary text-white">Giá</th>
                                <th scope="col" class="bg-primary text-white">SL bán/còn</th>
                                <th scope="col" class="bg-primary text-white">Mô tả</th>
                                <th scope="col" class="bg-primary text-white">Hình ảnh</th>
                                <th scope="col" class="bg-primary text-white"><span class="d-flex justify-content-center">Thao tác</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${ProductDetails}" varStatus="loop">
                                <tr class="align-item-center">
                                    <th scope="row">${loop.index + 1}</th>
                                    <td>${item.product.name}</td>
                                    <td style="min-width: 100px">${item.price}</td>
                                    <td style="min-width: 100px">${item.quantitySold} / ${item.quantityStock}</td>
                                    <td>
                                        <div class="row">
                                            <div>
                                                Kích thước: ${item.size}
                                            </div>
                                            <div>
                                                Màu sắc: ${item.color}
                                            </div>
                                            <div>
                                                Chi tiết: ${item.description}
                                            </div>
                                        </div>
                                    </td>
                                    <td><img src="assets/uploads/${item.pictureUrl}" class="img-thumbnail" style="width: 100px; height: 100px" alt="..."></td>
                                    <td>
                                        <div class="d-flex justify-content-center gap-1">
                                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Sửa"
                                                    data-bs-toggle="modal" data-bs-target="#EditModal" 
                                                    onclick="editProductDetail('${item.id}','${item.productId}','${item.price}','${item.size}','${item.color}','${item.quantitySold}','${item.quantityStock}','${item.description}')">
                                                <i class="fa-solid fa-pen-to-square"></i><!--
                                                -->                                            </button>
                                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Xoá" 
                                                    data-bs-toggle="modal" data-bs-target="#DeleteModal" onclick="deleteProductDetail('${item.id}')">
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
                        <form id="searchProDetailForm" action="productDetailManagement" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="searchProductDetailInput" name="searchProductDetailInput" hidden="">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="searchProductIdInput" name="searchProductIdInput" hidden="">
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
                        <form action="productDetailManagement" method="post" enctype="multipart/form-data">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÊM MỚI CHI TIẾT SẢN PHẨM</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="ADD" hidden="true">
                                </div>
                                <div class="row">
                                    <div class="col-6 form-group">
                                        <label for="addProductId">Sản phẩm</label>
                                        <select class="form-control" name="addProductId" id="addProductId">
                                            <c:forEach var="item" items="${Products}" varStatus="loop">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="addPrice">Giá tiền</label>
                                        <input type="text" class="form-control" id="addPrice" name="addPrice" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="addSize">Kích thước</label>
                                        <input type="number" class="form-control" id="addSize" name="addSize" value="0" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="addColor">Màu sắc</label>
                                        <input type="text" class="form-control" id="addColor" name="addColor"" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="addQuanSold">Số lượng đã bán</label>
                                        <input type="number" class="form-control" id="addQuanSold" name="addQuanSold" value="0" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="addQuanStock">Số lượng còn trong kho</label>
                                        <input type="number" class="form-control" id="addQuanStock" name="addQuanStock" value="0" required="true">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="addPicture">Hình ảnh</label>
                                        <input type="file" class="form-control" id="addPicture" name="addPicture" required="true">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="addDesc">Mô tả</label>
                                        <textarea rows="4" cols="50" class="form-control" id="addDesc" name="addDesc">
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
                        <form action="productDetailManagement" method="post" enctype="multipart/form-data">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÊM MỚI CHI TIẾT SẢN PHẨM</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="EDIT" hidden="true">
                                    <input type="text" class="form-control" id="editId" name="editId" hidden="true">
                                </div>
                                <div class="row">
                                    <div class="col-6 form-group">
                                        <label for="editProductId">Sản phẩm</label>
                                        <select class="form-control" name="editProductId" id="editProductId">
                                            <c:forEach var="item" items="${Products}" varStatus="loop">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="editPrice">Giá tiền</label>
                                        <input type="text" class="form-control" id="editPrice" name="editPrice" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="editSize">Kích thước</label>
                                        <input type="number" class="form-control" id="editSize" name="editSize" value="0" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="editColor">Màu sắc</label>
                                        <input type="text" class="form-control" id="editColor" name="editColor"" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="editQuanSold">Số lượng đã bán</label>
                                        <input type="number" class="form-control" id="editQuanSold" name="editQuanSold" value="0" required="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="editQuanStock">Số lượng còn trong kho</label>
                                        <input type="number" class="form-control" id="editQuanStock" name="editQuanStock" value="0" required="true">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="editPicture">Hình ảnh</label>
                                        <input type="file" class="form-control" id="editPicture" name="editPicture">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="editDesc">Mô tả</label>
                                        <textarea rows="4" cols="50" class="form-control" id="editDesc" name="editDesc">
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
                        <form action="productDetailManagement" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Bạn có chắc chắn muốn xoá sản phẩm này?
                                <div class="form-group">
                                    <input type="text" class="form-control" id="deleteId" name="deleteId" value="" hidden="">
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

//                                                        function categoryDetail(accountId, username) {
//                                                            document.getElementById('ban-modal-body').textContent = username;
//                                                            document.getElementById('banAccountId').value = accountId;
//                                                        }
//
                                                        function editProductDetail(id, productId, price, size, color, quantitySold, quantityStock, description) {
                                                            document.getElementById('editId').value = id;
                                                            document.getElementById('editProductId').value = productId;
                                                            document.getElementById('editPrice').value = price;
                                                            document.getElementById('editSize').value = size;
                                                            document.getElementById('editColor').value = color;
                                                            document.getElementById('editQuanSold').value = quantitySold;
                                                            document.getElementById('editQuanStock').value = quantityStock;
                                                            document.getElementById('editDesc').value = description;
                                                        }

                                                        function deleteProductDetail(deleteId) {
                                                            document.getElementById('deleteId').value = deleteId;
                                                        }

                                                        function searchProductDetail() {
                                                            document.getElementById('searchProductDetailInput').value = document.getElementById('searchProductDetail').value;
                                                            document.getElementById('searchProductIdInput').value = document.getElementById('searchProductId').value;
                                                            document.getElementById("searchProDetailForm").submit();
                                                        }
            </script>
    </body>
</html>

