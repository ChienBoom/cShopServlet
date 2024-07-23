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
        <title>Quản lý order</title>
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
                        <span class="text-primary" style="font-weight: bold;">QUẢN LÝ ORDER</span>
                        <div class="d-flex align-items-center">
                            <label style="margin-right: 10px;" for="searchStartDate">Từ ngày:</label>
                            <input type="date" class="border rounded" style="margin-right: 10px; min-width: 150px; height: 40px;" value="${searchStartDate}" 
                                   id="searchStartDate" name="searchStartDate">
                            <label style="margin-right: 10px;" for="searchEndDate">Đến ngày:</label>
                            <input type="date" class="border rounded" style="margin-right: 10px; min-width: 150px; height: 40px;" value="${searchEndDate}" 
                                   id="searchEndDate" name="searchEndDate" placeholder="Nhập tên sản phẩm">
                            <button type="button" style="margin-right: 10px"  class="btn btn-primary" data-toggle="tooltip" title="Tìm kiếm" 
                                    data-bs-toggle="modal" data-bs-target="#" onclick="searchOrderByDate()">
                                <i class="fa-solid fa-magnifying-glass"></i>
                                Tìm kiếm
                            </button>
                        </div>
                    </div>
                    <table class="table border" style="margin-top: 20px">
                        <thead> 
                            <tr>
                                <th scope="col" class="bg-primary text-white">STT</th>
                                <th scope="col" class="bg-primary text-white">Ngày đặt hàng</th>
                                <th scope="col" class="bg-primary text-white">Người đặt</th>
                                <th scope="col" class="bg-primary text-white">Email</th>
                                <th scope="col" class="bg-primary text-white">Số tiền</th>
                                <th scope="col" class="bg-primary text-white"><span class="d-flex justify-content-center">Thao tác</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${Orders}" varStatus="loop">
                                <tr class="align-item-center">
                                    <th scope="row">${loop.index + 1}</th>
                                    <td style="min-width: 100px">${item.orderAt}</td>
                                    <td style="min-width: 100px">${item.user.fullName}</td>
                                    <td style="min-width: 100px">${item.user.email}</td>
                                    <td style="min-width: 100px">${item.totalPrice}</td>
                                    <td>
                                        <div class="d-flex justify-content-center gap-1">
                                            <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Xem chi tiết"
                                                    onclick="showDetail('${item.id}')">
                                                <i class="fa-solid fa-circle-check"></i>
                                            </button>
                                            <c:if test="${item.status == 'CANCEL'}">
                                                <button type="button" class="btn btn-danger" data-toggle="tooltip" title="Trạng thái đang chờ xác nhận">
                                                    <i class="fa-regular fa-circle-xmark"></i>
                                                </button>
                                            </c:if>
                                            <c:if test="${item.status == 'PENDDING'}">
                                                <button type="button" class="btn btn-secondary" data-toggle="tooltip" title="Trạng thái đang chờ xác nhận">
                                                    <i class="fa-solid fa-spinner"></i>
                                                </button>
                                            </c:if>
                                            <c:if test="${item.status == 'DONE'}">
                                                <button type="button" class="btn btn-success" data-toggle="tooltip" title="Trạng thái đơn hàng đã được xác nhận">
                                                    <i class="fa-solid fa-circle-check"></i>
                                                </button>
                                            </c:if>

                                            <c:if test="${item.status == 'CANCEL'}">
                                                <button type="button" class="btn btn-primary" data-toggle="tooltip" title="Đặt lại đơn hàng"
                                                        data-bs-toggle="modal" data-bs-target="#ReOrderModal" onclick="reOrder('${item.id}')">
                                                    <i class="fa-solid fa-rotate-right"></i>
                                                </button>
                                            </c:if>

                                            <c:if test="${item.status == 'PENDDING'}">
                                                <button type="button" class="btn btn-danger" data-toggle="tooltip" title="Huỷ đơn hàng"
                                                        data-bs-toggle="modal" data-bs-target="#CancelModal" onclick="cancelOrder('${item.id}')">
                                                    <i class="fa-regular fa-circle-xmark"></i>
                                                </button>
                                            </c:if>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </main>
            </div>

            <jsp:include page="_footer.jsp"></jsp:include>

            <!-- Search Modal -->
            <div class="modal fade" id="SearchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form id="searchOrderForm" action="order" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="searchStartDateInput" name="searchStartDateInput" hidden="">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="searchEndDateInput" name="searchEndDateInput" hidden="">
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

            <!-- show Order Detail Modal -->
            <div class="modal fade" id="ShowDetailModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form id="ShowDetailForm" action="orderDetail" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="showOrderId" name="showOrderId" hidden="">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="SHOW-ORDER-DETAIL" hidden="">
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
            
            <!-- Cancel Modal -->
            <div class="modal fade" id="CancelModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="order" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Bạn có chắc chắn muốn huỷ đơn hàng này? <span id="delete-modal-body"></span>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="cancelOrderId" name="cancelOrderId" value="" hidden="">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="CANCEL" hidden="">
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
            
            <!-- ReOrder Modal -->
            <div class="modal fade" id="ReOrderModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="order" method="post">
                            <div class="modal-header bg-primary">
                                <h1 class="modal-title fs-5 text-white" id="exampleModalLabel">THÔNG BÁO</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Bạn có chắc chắn muốn huỷ đơn hàng này? <span id="delete-modal-body"></span>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="reOrderId" name="reOrderId" value="" hidden="">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="action" name="action" value="RE-ORDER" hidden="">
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

                                                            function showDetail(orderId) {
                                                                document.getElementById('showOrderId').value = orderId;
                                                                document.getElementById("ShowDetailForm").submit();
                                                            }

                                                            function searchOrderByDate() {
                                                                document.getElementById('searchStartDateInput').value = document.getElementById('searchStartDate').value;
                                                                document.getElementById('searchEndDateInput').value = document.getElementById('searchEndDate').value;
                                                                document.getElementById("searchOrderForm").submit();
                                                            }
                                                            
                                                            function reOrder(orderId) {
                                                                document.getElementById('reOrderId').value = orderId;
                                                            }
                                                            
                                                            function cancelOrder(orderId) {
                                                                document.getElementById('cancelOrderId').value = orderId;
                                                            }

            </script>
    </body>
</html>

