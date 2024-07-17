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
        <title>Giỏ hàng</title>
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

                <input type="text" id="OrderUsernameInput" name="OrderUsernameInput" value="${User.username}" hidden="">
                <input type="text" id="orderAddressInput" name="orderAddressInput" value="${User.address}" hidden="true">

                <main class="container" style="margin-top: 50px">
                    <div class="d-flex justify-content-between">
                        <span class="text-primary" style="font-weight: bold;">QUẢN LÝ GIỎ HÀNG</span>
                    </div>
                    <table class="table border" style="margin-top: 20px">
                        <thead> 
                            <tr>
                                <th scope="col" class="bg-primary text-white">STT</th>
                                <th scope="col" class="bg-primary text-white">Tên sản phẩm</th>
                                <th scope="col" class="bg-primary text-white">Hình ảnh</th>
                                <th scope="col" class="bg-primary text-white">SL</th>
                                <th scope="col" class="bg-primary text-white">Giá</th>
                                <th scope="col" class="bg-primary text-white">Thành tiền</th>
                                <th scope="col" class="bg-primary text-white"><span class="d-flex justify-content-center">Thao tác</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${AddToCarts}" varStatus="loop">
                                <tr class="align-item-center">
                                    <th scope="row">${loop.index + 1}</th>
                                    <td>${item.productDetail.product.name}</td>
                                    <td><img src="assets/uploads/${item.productDetail.product.pictureUrl}" class="img-thumbnail" style="width: 100px; height: 100px" alt="..."></td>
                                    <td>${item.quantity}</td>
                                    <td>${item.productDetail.price}</td>
                                    <td>${item.productDetail.price * item.quantity}</td>
                                    <td>
                                        <div class="d-flex justify-content-center gap-1">
                                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Đặt mua"
                                                    data-bs-toggle="modal" data-bs-target="#OrderModal" 
                                                    onclick="orderProduct('${item.productDetail.product.name}', '${item.quantity}', '${item.productDetail.price}', '${item.productDetail.size}', '${item.productDetail.color}', '${item.productDetail.id}', '${item.id}')">
                                                <i class="fa-solid fa-circle-check"></i>
                                            </button>
                                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Sửa"
                                                    data-bs-toggle="modal" data-bs-target="#EditModal" onclick="editAddToCart('${item.id}', '${item.productDetail.product.name}', '${item.quantity}')">
                                                <i class="fa-solid fa-pen-to-square"></i>
                                            </button>
                                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Xoá" 
                                                    data-bs-toggle="modal" data-bs-target="#DeleteModal" onclick="deleteAddToCart('${item.id}', '${item.productDetail.product.name}')">
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

            <jsp:include page="_footer.jsp"></jsp:include>

            <!-- Order Modal -->
            <div class="modal fade" id="OrderModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="order" method="post" enctype="multipart/form-data">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">ĐẶT MUA</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="ADD" hidden="true">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="orderAddToCartId" name="orderAddToCartId" hidden="true">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="orderProductDetailId" name="orderProductDetailId" hidden="true">
                                </div>
                                <div class="row">
                                    <div class="col-6 form-group">
                                        <label for="orderUsername">Tên người dùng</label>
                                        <input type="text" class="form-control" id="orderUsername" name="orderUsername" readonly="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="orderAddress">Địa chỉ</label>
                                        <input type="text" class="form-control" id="orderAddress" name="orderAddress" readonly="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="orderProductName">Tên sản phẩm</label>
                                        <input type="text" class="form-control" id="orderProductName" name="orderProductName" readonly="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="orderQuantity">Số lượng sản phẩm</label>
                                        <input type="number" class="form-control" id="orderQuantity" name="orderQuantity" value="0" readonly="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="orderColor">Màu sắc</label>
                                        <input type="text" class="form-control" id="orderColor" name="orderColor" readonly="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="orderSize">Kích thước</label>
                                        <input type="text" class="form-control" id="orderSize" name="orderSize" readonly="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="orderPrice">Giá tiền</label>
                                        <input type="text" class="form-control" id="orderPrice" name="orderPrice" readonly="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="orderShip">Phí vận chuyển</label>
                                        <input type="text" class="form-control" id="orderShip" name="orderShip" readonly="true">
                                    </div>
                                    <div class="col-6 form-group">
                                        <label for="orderTotalPrice">Tổng tiền</label>
                                        <input type="text" class="form-control" id="orderTotalPrice" name="orderTotalPrice" readonly="true">
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="orderDescription">Chú thích cho người bán</label>
                                        <textarea rows="4" cols="50" class="form-control" id="orderDescription" name="orderDescription">
                                        </textarea>  
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                                <button type="submit" class="btn btn-primary">Đặt mua</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Edit Modal -->
            <div class="modal fade" id="EditModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="addToCart" method="post" enctype="multipart/form-data">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">SỬA GIỎ HÀNG</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="EDIT" hidden="true">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="editAddToCartId" name="editAddToCartId" value="" hidden="">
                                </div>
                                Thay đổi số lượng cho sản phẩm <span id="edit-modal-body"></span>
                                <div class="row">

                                    <div class="col-6 form-group">
                                        <label for="addNumProCate">Số lượng sản phẩm</label>
                                        <input type="number" class="form-control" id="editQuantity" name="editQuantity" value="0">
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
                        <form action="addToCart" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Bạn có chắc chắn muốn xoá sản phẩm <span id="delete-modal-body"></span> khỏi giỏ hàng
                                <div class="form-group">
                                    <input type="text" class="form-control" id="deleteAddToCartId" name="deleteAddToCartId" value="" hidden="">
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

            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
            <script>
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

                                                        function orderProduct(name, quantity, price, size, color, productDetailId, addToCartId) {
                                                            document.getElementById('orderProductName').value = name;
                                                            document.getElementById('orderQuantity').value = quantity;
                                                            document.getElementById('orderPrice').value = price;
                                                            var priceDe = price;
                                                            var shipPriceDe = price/10;
                                                            var totalPriceDe = (priceDe * quantity + shipPriceDe);
                                                            document.getElementById('orderShip').value = shipPriceDe;
                                                            document.getElementById('orderTotalPrice').value = totalPriceDe
                                                            document.getElementById('orderSize').value = size;
                                                            document.getElementById('orderColor').value = color;
                                                            document.getElementById('orderUsername').value = document.getElementById('OrderUsernameInput').value;
                                                            document.getElementById('orderAddress').value = document.getElementById('orderAddressInput').value;
                                                            document.getElementById('orderProductDetailId').value = productDetailId; 
                                                            document.getElementById('orderAddToCartId').value = addToCartId;
                                                        }

                                                        function editAddToCart(id, name, quantity) {
                                                            document.getElementById('edit-modal-body').textContent = name;
                                                            document.getElementById('editAddToCartId').value = id;
                                                            document.getElementById('editQuantity').value = quantity;
                                                        }

                                                        function deleteAddToCart(deleteAddToCartId, productName) {
                                                            document.getElementById('delete-modal-body').textContent = productName;
                                                            document.getElementById('deleteAddToCartId').value = deleteAddToCartId;
                                                        }

            </script>
    </body>
</html>

