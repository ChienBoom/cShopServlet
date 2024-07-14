/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.OrderDAO;
import DAOs.ProductDAO;
import DAOs.ProductDetailDAO;
import Models.ProductDetail;
import Services.UploadService;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
//import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhChien
 */
@MultipartConfig()
@WebServlet(urlPatterns = {"/orderManagement"})
public class OrderManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public OrderManagementServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
        try {
//            HttpSession session = request.getSession();
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/orderManagement.jsp");
            request.setAttribute("Orders", OrderDAO.getAllOrder());
            request.setAttribute("searchStartDate", LocalDate.now().minusMonths(1));
            request.setAttribute("searchEndDate", LocalDate.now());
            request.setAttribute("USERNAME", userNameGlo);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/orderManagement.jsp");
            request.setAttribute("USERNAME", userNameGlo);
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
        try {
            String action = request.getParameter("action");
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/orderManagement.jsp");
            switch (action) {
                case "ADD":
                    // Lấy thông tin file
                    Part picture = request.getPart("addPicture");
                    UploadService uploadService = new UploadService();
                    String fileName = uploadService.UploadPicture(request, picture);

                    ProductDetail productDetail = new ProductDetail(1, request.getParameter("addSize"), request.getParameter("addColor"),
                            fileName, BigDecimal.valueOf(Double.parseDouble(request.getParameter("addPrice"))),
                            Integer.parseInt(request.getParameter("addQuanSold")), Integer.parseInt(request.getParameter("addQuanStock")),
                            request.getParameter("addDesc"), Long.parseLong(request.getParameter("addProductId")));
                    boolean addProductDetail = ProductDetailDAO.addProductDetail(productDetail);
                    request.setAttribute("Products", ProductDAO.getAllProduct());
                    request.setAttribute("ProductDetails", ProductDetailDAO.getAllProductDetail());
                    request.setAttribute("searchInput", "");
                    request.setAttribute("searchProductIdInput", 999);
                    if (addProductDetail) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Thêm mới chi tiết sản phẩm thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Thêm mới chi tiết sản phẩm thất bại");
                    }
                    break;
                case "EDIT":
                    // Lấy thông tin file
                    Part editPicture = request.getPart("editPicture");
                    String editFileName = "NULL";
                    if (!editPicture.getName().equals("")) {
                        UploadService editUploadService = new UploadService();
                        editFileName = editUploadService.UploadPicture(request, editPicture);
                    }
                    ProductDetail editProductDetail = new ProductDetail(Long.parseLong(request.getParameter("editId")), request.getParameter("editSize"), request.getParameter("editColor"),
                            editFileName, BigDecimal.valueOf(Double.parseDouble(request.getParameter("editPrice"))),
                            Integer.parseInt(request.getParameter("editQuanSold")), Integer.parseInt(request.getParameter("editQuanStock")),
                            request.getParameter("editDesc"), Long.parseLong(request.getParameter("editProductId")));
                    boolean editProDetail = ProductDetailDAO.updateProductDetail(editProductDetail);
                    request.setAttribute("Products", ProductDAO.getAllProduct());
                    request.setAttribute("ProductDetails", ProductDetailDAO.getAllProductDetail());
                    request.setAttribute("searchInput", "");
                    request.setAttribute("searchProductIdInput", 999);
                    if (editProDetail) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Sửa thông tin chi tiết sản phẩm thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Sửa thông tin chi tiết sản phẩm thất bại");
                    }
                    break;
                case "SEARCH":
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    Date searchStartDateInput = dateFormat.parse(request.getParameter("searchStartDateInput"));
                    Date searchEndDateInput = dateFormat.parse(request.getParameter("searchEndDateInput"));
                    request.setAttribute("Orders", OrderDAO.searchOrder(new java.sql.Date(searchStartDateInput.getTime()), new java.sql.Date(searchEndDateInput.getTime())));
                    request.setAttribute("searchStartDate", LocalDate.parse(request.getParameter("searchStartDateInput"), formatter));
                    request.setAttribute("searchEndDate", LocalDate.parse(request.getParameter("searchEndDateInput"), formatter));
                    break;
//                case "SHOW-PRODUCT-CATEGORY":
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                    Date searchStartDateInput = dateFormat.parse(request.getParameter("searchStartDateInput"));
//                    Date searchEndDateInput = dateFormat.parse(request.getParameter("searchEndDateInput"));
//                    request.setAttribute("Orders", OrderDAO.searchOrder(new java.sql.Date(searchStartDateInput.getTime()) , new java.sql.Date(searchEndDateInput.getTime())));
//                    break;
                case "DELETE":
                    long deleteProDetailId;
                    boolean deleteProductDetail = false;
                    String deleteProDetailIdPara = request.getParameter("deleteId");
                    deleteProDetailId = Long.parseLong(deleteProDetailIdPara);
                    deleteProductDetail = ProductDetailDAO.deleteProductDetail(deleteProDetailId);
                    request.setAttribute("Products", ProductDAO.getAllProduct());
                    request.setAttribute("ProductDetails", ProductDetailDAO.getAllProductDetail());
                    request.setAttribute("searchInput", "");
                    request.setAttribute("searchProductIdInput", 999);
                    if (deleteProductDetail) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Xoá chi tiết sản phẩm thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Xoá chi tiết sản phẩm thất bại");
                    }
                    break;
                default:
                    break;
            }
            request.setAttribute("USERNAME", userNameGlo);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            try {
                System.out.println(e);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/orderManagement.jsp");
                request.setAttribute("Products", ProductDAO.getAllProduct());
                request.setAttribute("ProductDetails", ProductDetailDAO.getAllProductDetail());
                request.setAttribute("searchInput", "");
                request.setAttribute("searchProductIdInput", 999);
                request.setAttribute("STATUS", "ERROR");
                request.setAttribute("MESSAGE", "Lỗi hệ thống");
                request.setAttribute("USERNAME", userNameGlo);
                dispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MemberManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MemberManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
