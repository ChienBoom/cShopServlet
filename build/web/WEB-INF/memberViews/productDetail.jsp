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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${Product.name}</title>
    </head>
    <style>
        .profile-image {
            width: 400px;
            height: 400px;
            border-radius: 2%;
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
                    <input type="text" class="form-control" id="productId" name="productId" value="${Product.id}" hidden="">
                    <div class="row">
                        <div class="col-4 d-flex flex-column align-items-center">
                            <img src="../assets/uploads/${Product.pictureUrl}" alt="Profile Image" class="profile-image border border-primary">
                        </div>
                        <div class="col-8 d-flex flex-column" >
                            <div class="fs-4 fw-semibold fst-italic">${Product.name}</div>
                            <div style="margin-top: 10px"><span class="fw-medium">Số lượng đã bán: </span>${Product.quantitySold}</div>
                            <div style="margin-top: 10px"><span class="fw-medium">Số lượng còn trong kho: </span>${Product.quantityStock}</div>
                            <div style="margin-top: 10px"><span class="fw-medium">Mô tả: </span>${Product.description}</div>
                            <select class="border rounded" style="min-width: 200px; max-width: 400px; height: 50px; margin-top: 20px" 
                                    name="selectProductDetail" id="selectProductDetail" onchange="ChangeProDetailSel()">
                                <c:forEach var="item" items="${ProductDetails}" varStatus="loop">
                                    <option value="${item.id}-${item.price}">${item.size} - ${item.color}</option>
                                </c:forEach>
                            </select>
                            <div style="margin-top: 10px"><span class="fw-medium">Số lượng: </span>
                                <input type="number" value="1" class="border rounded"
                                       style="min-width: 100px; max-width: 100px; height: 50px;" id="quantity" name="quantity"/>
                            </div>

                            <div class="fs-3 fst-italic fw-semibold text-primary" style="margin-top: 10px" id="price"></div>
                            <div style="margin-top: 30px">
                                <button type="button" class="btn btn-outline-primary" style="width: 200px; height: 50px"
                                        data-bs-toggle="modal" data-bs-target="#" onclick="addToCart()">Thêm vào giỏ hàng</button>
                                <button type="button" class="btn btn-primary" style="width: 200px; height: 50px"
                                        data-bs-toggle="modal" data-bs-target="#" onclick="buyNow()">Mua ngay</button>
                            </div>
                        </div>
                    </div>
                </div>
        </main>
        <jsp:include page="_footer.jsp"></jsp:include>

        <!-- add to cart Modal -->
        <div class="modal fade" id="AddToCartModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="addToCartForm" action="/cShop/addToCart" method="post">
                        <div class="modal-header bg-primary">
                            <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" class="form-control" id="productDetailIdInput" name="productDetailIdInput" hidden="">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="productIdInput" name="productIdInput" hidden="">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="quantityInput" name="quantityInput" hidden="">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="action" name="action" value="ADD-TO-CART" hidden="">
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
                                            setTimeout(function () {
                                                // Select all alerts with the 'alert' class
                                                var alerts = document.querySelectorAll('.alert');
                                                alerts.forEach(function (alert) {
                                                    // Hide each alert
                                                    $(alert).alert('close');
                                                });
                                            }, 2000);

                                            function ChangeProDetailSel() {
                                                var selectElement = document.getElementById('selectProductDetail');
                                                var selectedValue = selectElement.value;

                                                var parts = selectedValue.split('-');
                                                var price = parts[1];

                                                var priceDisplayElement = document.getElementById('price');
                                                priceDisplayElement.textContent = price + " VNĐ";
                                            }

                                            function addToCart() {
                                                var selectElement = document.getElementById('selectProductDetail');
                                                var selectedValue = selectElement.value;

                                                var parts = selectedValue.split('-');
                                                var productDetailId = parts[0];

                                                document.getElementById('productIdInput').value = document.getElementById('productId').value;
                                                document.getElementById('productDetailIdInput').value = productDetailId;
                                                document.getElementById('quantityInput').value = document.getElementById('quantity').value;
                                                document.getElementById("addToCartForm").submit();
                                            }

                                            document.addEventListener('DOMContentLoaded', (event) => {
                                                ChangeProDetailSel(); // Gọi hàm để hiển thị giá trị price của item được chọn đầu tiên
                                            });
        </script>
    </body>

</html>
