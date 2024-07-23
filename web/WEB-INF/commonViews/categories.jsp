<%-- 
    Document   : home
    Created on : Jul 8, 2024, 11:26:56 AM
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
        <title>Danh mục</title>
    </head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .hover-item {
            transition: all 0.3s ease;
        }
        .hover-item:hover {
            border: 1px solid #007bff; /* Màu primary của Bootstrap */
            opacity: 0.5;
        }
    </style>
    <body class="d-flex flex-column min-vh-100">
        <jsp:include page="_header.jsp"></jsp:include>
            <main class="flex-fill" style="background-color: #f5f5f5">
                <div class="d-flex align-items-center justify-content-center" style="margin-top: 50px">
                    <input type="text" class="border rounded" style="margin-right: 10px; min-width: 600px; height: 40px;" value="${searchInput}" 
                       id="searchTextInput" name="searchTextInput" placeholder="Nhập tên danh mục">
                <button type="button" style="margin-right: 10px"  class="btn btn-primary" data-toggle="tooltip" title="Tìm kiếm" 
                        data-bs-toggle="modal" data-bs-target="#" onclick="searchFun()">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    Tìm kiếm
                </button>
            </div>

            <div class="container" style="margin-top: 50px">
                <div class="border-bottom border-primary text-center"  style="margin-top: 30px">
                    <span class="text-primary fs-4 fw-semibold">DANH MỤC</span>
                </div>
                <div class="row g-3" style="margin-top: 20px">
                    <c:forEach var="item" items="${Categories}" varStatus="loop">
                        <div class="hover-item col-2 card">
                            <a href="${pageContext.request.contextPath}/product/${item.id}">
                                <img src="assets/uploads/${item.pictureUrl}" class="img-thumbnail card-img-top" style=" height: 150px" alt="...">
                                <div class="card-body">
                                    <div class="text-center" style="font-size: 10px">${item.name}</div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </main>
        <jsp:include page="_footer.jsp"></jsp:include>

        <!-- Search Modal -->
        <div class="modal fade" id="SearchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="searchForm" action="category" method="post">
                        <div class="modal-header bg-primary">
                            <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" class="form-control" id="searchInput" name="searchInput" hidden="">
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

                            function OrderDetail(deleteId) {
                                document.getElementById('deleteId').value = deleteId;
                            }

                            function searchFun() {
                                document.getElementById('searchInput').value = document.getElementById('searchTextInput').value;
                                document.getElementById("searchForm").submit();
                            }
        </script>    
    </body>
</html>
