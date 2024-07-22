/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.CategoryDAO;
import DAOs.ProductDAO;
import DAOs.ProductDetailDAO;
import Models.Product;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhChien
 */
@MultipartConfig()
@WebServlet(urlPatterns = {"/productDetailManagement"})
public class ProductDetailManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ProductDetailManagementServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
        String roleUser = (String) getServletContext().getAttribute("ROLE");
        try {
            RequestDispatcher dispatcher;
            if (roleUser == null || roleUser.equals("MEMBER")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/productDetailManagement.jsp");
            }
            request.setAttribute("Products", ProductDAO.getAllProduct());
            request.setAttribute("ProductDetails", ProductDetailDAO.getAllProductDetail());
            request.setAttribute("searchInput", "");
            request.setAttribute("searchProductIdInput", 999);
            request.setAttribute("USERNAME", userNameGlo);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/error.jsp");
            request.setAttribute("USERNAME", userNameGlo);
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
        String roleUser = (String) getServletContext().getAttribute("ROLE");
        try {
            RequestDispatcher dispatcher;
            if (roleUser == null || roleUser.equals("MEMBER")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/productDetailManagement.jsp");
            }
            String action = request.getParameter("action");
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
                    if (editPicture.getSubmittedFileName() != null && !editPicture.getSubmittedFileName().isEmpty()) {
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
                    String searchInput = request.getParameter("searchProductDetailInput").trim();
                    long searchProductIdInput = Long.parseLong(request.getParameter("searchProductIdInput"));
                    request.setAttribute("Products", ProductDAO.getAllProduct());
                    request.setAttribute("ProductDetails", ProductDetailDAO.searchProductDetail(searchInput, searchProductIdInput));
                    request.setAttribute("searchInput", searchInput);
                    request.setAttribute("searchProductId", searchProductIdInput);
                    break;
                case "SHOW-PRODUCT-DETAIL":
                    long showProductId = Long.parseLong(request.getParameter("showProductId"));
                    request.setAttribute("Products", ProductDAO.getAllProduct());
                    request.setAttribute("ProductDetails", ProductDetailDAO.getProductDetailsByProductId(showProductId));
                    request.setAttribute("searchInput", "");
                    request.setAttribute("searchProductId", showProductId);
                    break;
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
            System.out.println(e);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/error.jsp");
            request.setAttribute("STATUS", "ERROR");
            request.setAttribute("MESSAGE", "Lỗi hệ thống");
            request.setAttribute("USERNAME", userNameGlo);
            dispatcher.forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
