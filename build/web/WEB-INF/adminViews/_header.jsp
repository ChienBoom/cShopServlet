<%-- 
    Document   : _header
    Created on : Jul 7, 2024, 8:38:36 PM
    Author     : MinhChien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header class="fixed-top">
            <div class="row align-items-center bg-primary">
                <div class="col-4 col-sm-4 col-md-4 text-light font-italic" style="padding-left: 100px;">CSHOP ADMIN</div>
                <div class="col-6 col-sm-6 col-md-6">
                    <ul class="nav">
                        <li class="nav-item">
                            <a class="nav-link text-light" href="${pageContext.request.contextPath}/home">Trang chủ</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="${pageContext.request.contextPath}/categoryManagement">Danh mục</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="${pageContext.request.contextPath}/productManagement">Sản phẩm</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="${pageContext.request.contextPath}/orderManagement">Order</a>
                        </li>
                    </ul>
                </div>
                <div class="col-2 col-sm-2 col-md-2">
                    <div class="nav-item dropdown text-light">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            ${USERNAME}
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile">Thông tin</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
    </body>
</html>
