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
                        <canvas id="myChart" width="200" height="200"></canvas>
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

        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var ctx = document.getElementById('myChart').getContext('2d');
                var myChart = new Chart(ctx, {
                    type: 'bar', // Loại biểu đồ (ví dụ: bar, line, pie, ...)
                    data: {
                        labels: ['Label 1', 'Label 2', 'Label 3', 'Label 4', 'Label 5'], // Nhãn của các dòng
                        datasets: [{
                                label: 'Dataset',
                                data: [10, 20, 30, 40, 50], // Dữ liệu từ servlet
                                backgroundColor: 'rgba(54, 162, 235, 0.2)', // Màu nền
                                borderColor: 'rgba(54, 162, 235, 1)', // Màu viền
                                borderWidth: 1 // Độ rộng viền
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true // Bắt đầu từ 0 trên trục y
                            }
                        }
                    }
                });
            });
        </script>
    </body>

</html>
