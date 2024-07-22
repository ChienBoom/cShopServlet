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
                <div>${Name}</div>
                <div class="container" style="margin-top: 50px">
                    <div class="row">
                        <div class="col-6">
                            <div class="fw-semibold fs-4 text-primary">THỐNG KÊ DOANH THU</div>
                            <div class="fw-semibold fs-5 text-primary" style="margin-top: 50px">Lựa chọn năm thống kê</div>
                            <select class="rounded" style="height: 50px; min-width: 200px" name="optionYears" id="optionYears" onchange="handleChangeSelect()">
                                <c:if test="${OptionYearSelected == '2023'}">
                                    <option value="2023" selected="">2023</option>
                                    <option value="2024">2024</option>
                                </c:if>
                                <c:if test="${OptionYearSelected == '2024'}">
                                    <option value="2023">2023</option>
                                    <option value="2024" selected="">2024</option>
                                </c:if>
                            </select>

                            <div class="fw-semibold fs-5 text-primary" style="margin-top: 50px">Lựa chọn loại thống kê</div>
                            <select class="rounded" style="height: 50px; min-width: 200px" name="optionTypes" id="optionTypes" onchange="handleChangeSelect()">
                                <c:if test="${OptionTypeSelected == 'MONTH'}">
                                    <option value="MONTH" selected="">Thống kê doanh thu theo tháng</option>
                                    <option value="CATEGORY">Thống kê doanh thu theo danh mục</option>
                                    <option value="PRODUCT">Thống kê doanh thu theo sản phẩm</option>
                                </c:if>
                                <c:if test="${OptionTypeSelected == 'CATEGORY'}">
                                    <option value="MONTH">Thống kê doanh thu theo tháng</option>
                                    <option value="CATEGORY" selected="">Thống kê doanh thu theo danh mục</option>
                                    <option value="PRODUCT">Thống kê doanh thu theo sản phẩm</option>
                                </c:if>
                                <c:if test="${OptionTypeSelected == 'PRODUCT'}">
                                    <option value="MONTH">Thống kê doanh thu theo tháng</option>
                                    <option value="CATEGORY">Thống kê doanh thu theo danh mục</option>
                                    <option value="PRODUCT" selected="">Thống kê doanh thu theo sản phẩm</option>
                                </c:if>
                            </select>

                            <div class="fw-semibold fs-5 text-primary" style="margin-top: 50px">Lựa chọn loại biểu đồ</div>
                            <select class="rounded" style="height: 50px; min-width: 200px" name="optionCharts" id="optionCharts" onchange="handleChangeSelect()">
                                <c:if test="${OptionChartSelected == 'bar'}">
                                    <option value="bar" selected="">Biểu đồ cột</option>
                                    <option value="line">Biểu đồ đường</option>
                                    <option value="pie">Biểu đồ tròn</option>
                                </c:if>
                                <c:if test="${OptionChartSelected == 'line'}">
                                    <option value="bar">Biểu đồ cột</option>
                                    <option value="line" selected="">Biểu đồ đường</option>
                                    <option value="pie">Biểu đồ tròn</option>
                                </c:if>
                                <c:if test="${OptionChartSelected == 'pie'}">
                                    <option value="bar">Biểu đồ cột</option>
                                    <option value="line">Biểu đồ đường</option>
                                    <option value="pie" selected="">Biểu đồ tròn</option>
                                </c:if>
                            </select>
                            
                            <div style="margin-top: 50px">
                                <button type="button" class="btn btn-primary" onclick="exportExcel()">Xuất file Excel</button>
                            </div>
                            
                        </div>
                        <div class="col-6" style="height: 600px">
                            <canvas  id="myChart" width="200" height="200"></canvas>
                        </div>
                    </div>
                </div>
        </main>
        <jsp:include page="_footer.jsp"></jsp:include>

            <!-- Change Options Modal -->
            <div class="modal fade" id="ChangeOptionModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form id="changeOptionForm" action="revenue" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel"></h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="CHANGE-OPTIONS" hidden="true">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="revenueOptionType" name="revenueOptionType" hidden="true">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="revenueOptionYear" name="revenueOptionYear" hidden="true">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="revenueOptionChart" name="revenueOptionChart" hidden="true">
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

            <!-- Export Modal -->
            <div class="modal fade" id="ChangeOptionModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form id="exportForm" action="revenue" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel"></h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="EXPORT-EXCEL" hidden="true">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="exOptionType" name="exOptionType" hidden="true">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="exOptionYear" name="exOptionYear" hidden="true">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="exOptionChart" name="exOptionChart" hidden="true">
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
                                                                var data = JSON.parse('${Data}')
                                                                var typeChart = '${OptionChartSelected}'

                                                                document.addEventListener('DOMContentLoaded', function () {
                                                                    var ctx = document.getElementById('myChart').getContext('2d');
                                                                    var myChart = new Chart(ctx, {
                                                                        type: typeChart, // Loại biểu đồ (ví dụ: bar, line, pie, ...)
                                                                        data: {
                                                                            labels: data.Labels,
                                                                            datasets: [{
                                                                                    label: 'Doanh thu',
                                                                                    data: data.Data, // Dữ liệu từ servlet
                                                                                    backgroundColor: 'rgba(54, 162, 235, 0.2)', // Màu nền
                                                                                    borderColor: 'rgba(54, 162, 235, 1)', // Màu viền
                                                                                    borderWidth: 1 // Độ rộng viền
                                                                                }]
                                                                        },
                                                                        options: {
                                                                            scales: {
                                                                                y: {
                                                                                    beginAtZero: true
                                                                                }
                                                                            }
                                                                        }
                                                                    });
                                                                });

                                                                function handleChangeSelect() {
                                                                    document.getElementById('revenueOptionType').value = document.getElementById('optionTypes').value;
                                                                    document.getElementById('revenueOptionYear').value = document.getElementById('optionYears').value;
                                                                    document.getElementById('revenueOptionChart').value = document.getElementById('optionCharts').value;
                                                                    document.getElementById("changeOptionForm").submit();
                                                                }

                                                                function exportExcel() {
                                                                    document.getElementById('exOptionType').value = document.getElementById('optionTypes').value;
                                                                    document.getElementById('exOptionYear').value = document.getElementById('optionYears').value;
                                                                    document.getElementById('exOptionChart').value = document.getElementById('optionCharts').value;
                                                                    document.getElementById("exportForm").submit();
                                                                }


        </script>
    </body>

</html>
