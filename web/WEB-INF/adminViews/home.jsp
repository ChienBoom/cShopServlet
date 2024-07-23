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
        <link rel="icon" type="image/x-icon" href="assets/logo/logo.jpg">
        <title>Trang chủ</title>
    </head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <body class="d-flex flex-column min-vh-100">
        <jsp:include page="_header.jsp"></jsp:include>
            <main class="flex-fill" style="padding-top: 56px; background-color: #f5f5f5">
                <div class="container" style="margin-top: 50px">
                    <div class="row g-3">
                        <div class="col-3 card">
                            <a href="${pageContext.request.contextPath}/memberManagement">
                            <img src="assets/pictures/home-nhansu.jpg" class="img-thumbnail card-img-top" style=" height: 300px" alt="...">
                            <div class="card-body">
                                <h5 class="card-title text-center">QUẢN LÝ NGƯỜI DÙNG</h5>
                            </div>
                        </a>
                    </div>
                    <div class="card col-3">
                        <a href="${pageContext.request.contextPath}/categoryManagement">
                            <img src="assets/pictures/home-danhmuc.jpg" class="img-thumbnail card-img-top" style=" height: 300px" alt="...">
                            <div class="card-body">
                                <h5 class="card-title text-center">QUẢN LÝ DANH MỤC</h5>
                            </div>
                        </a>
                    </div>
                    <div class="card col-3">
                        <a href="${pageContext.request.contextPath}/productManagement">
                            <img src="assets/pictures/home-sanpham.jpg" class="img-thumbnail card-img-top" style=" height: 300px" alt="...">
                            <div class="card-body">
                                <h5 class="card-title text-center">QUẢN LÝ SẢN PHẨM</h5>
                            </div>
                        </a>
                    </div>
                    <div class="card col-3">
                        <a href="${pageContext.request.contextPath}/productDetailManagement">
                            <img src="assets/pictures/home-order.jpeg" class="img-thumbnail card-img-top" style=" height: 300px" alt="...">
                            <div class="card-body">
                                <h5 class="card-title text-center">QUẢN LÝ CHI TIẾT SẢN PHẨM</h5>
                            </div>
                        </a>
                    </div>
                    <div class="col-3 card">
                        <a href="${pageContext.request.contextPath}/orderManagement">
                            <img src="assets/pictures/home-orderDetail.jpg" class="img-thumbnail card-img-top" style=" height: 300px" alt="...">
                            <div class="card-body">
                                <h5 class="card-title text-center">QUẢN LÝ ORDER</h5>
                            </div>
                        </a>
                    </div>
                    <div class="card col-3">
                        <a href="${pageContext.request.contextPath}/revenue">
                            <img src="assets/pictures/home-doanhthu.jpg" class="img-thumbnail card-img-top" style=" height: 300px" alt="...">
                            <div class="card-body">
                                <h5 class="card-title text-center">QUẢN LÝ DOANH THU</h5>
                            </div>
                        </a>
                    </div>
                </div>
            </div>

        </main>
        <jsp:include page="_footer.jsp"></jsp:include>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>

</html>
