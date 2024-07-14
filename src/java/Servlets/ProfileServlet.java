/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.CategoryDAO;
import DAOs.ProductDAO;
import Models.Product;
import Services.UploadService;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhChien
 */
@MultipartConfig()
@WebServlet(urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ProfileServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
        try {
//            HttpSession session = request.getSession();
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/productManagement.jsp");
            request.setAttribute("Categories", CategoryDAO.getAllCategory());
            request.setAttribute("Products", ProductDAO.getAllProduct());
            request.setAttribute("USERNAME", userNameGlo);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/productManagement.jsp");
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
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/productManagement.jsp");
            switch (action) {
                case "ADD":
                    // Lấy thông tin file
                    Part picture = request.getPart("addProPicture");
                    UploadService uploadService = new UploadService();
                    String fileName = uploadService.UploadPicture(request, picture);

                    Product product = new Product(1, request.getParameter("addProName"), fileName,
                            Integer.parseInt(request.getParameter("addProQuanSold")), Integer.parseInt(request.getParameter("addProQuanStock")),
                            request.getParameter("addProDesc").trim(), Long.parseLong(request.getParameter("addProCateId")), false);
                    boolean addCate = ProductDAO.addProduct(product);
                    request.setAttribute("Categories", CategoryDAO.getAllCategory());
                    request.setAttribute("Products", ProductDAO.getAllProduct());
                    if (addCate) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Thêm mới sản phẩm thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Thêm mới sản phẩm thất bại");
                    }
                    break;
                case "EDIT":
                    // Lấy thông tin file
                    Part editPicture = request.getPart("editProPicture");
                    String editFileName = "NULL";
                    if (editPicture.getName().equals("")) {
                        UploadService editUploadService = new UploadService();
                        editFileName = editUploadService.UploadPicture(request, editPicture);
                    }
                    Product editProduct = new Product(Long.parseLong(request.getParameter("editProId")), request.getParameter("editProName"), editFileName,
                            Integer.parseInt(request.getParameter("editProQuanSold")), Integer.parseInt(request.getParameter("editProQuanStock")),
                            request.getParameter("editProDesc").trim(), Long.parseLong(request.getParameter("editProCateId")), false);
                    boolean editPro = ProductDAO.updateProduct(editProduct);
                    request.setAttribute("Categories", CategoryDAO.getAllCategory());
                    request.setAttribute("Products", ProductDAO.getAllProduct());
                    if (editPro) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Sửa thông tin sản phẩm thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Sửa thông tin sản phẩm thất bại");
                    }
                    break;
                case "SEARCH":
                    String searchInput = request.getParameter("searchProductInput").trim();
                        request.setAttribute("Products", ProductDAO.searchProduct(searchInput,999));
                    break;
                case "SHOW-PRODUCT-CATEGORY":
                    long showProCateId = Long.parseLong(request.getParameter("showProCateId"));
                    request.setAttribute("Categories", CategoryDAO.getAllCategory());
                    request.setAttribute("Products", ProductDAO.getProductsByCategoryId(showProCateId));
                    break;
                case "DELETE":
                    long deleteProId;
                    boolean deleteProduct = false;
                    String deleteProIdPara = request.getParameter("deleteProId");
                    deleteProId = Long.parseLong(deleteProIdPara);
                    deleteProduct = ProductDAO.deleteProduct(deleteProId);
                    request.setAttribute("Categories", CategoryDAO.getAllCategory());
                    request.setAttribute("Products", ProductDAO.getAllProduct());
                    if (deleteProduct) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Xoá sản phẩm thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Xoá sản phẩm thất bại");
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
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/productManagement.jsp");
                request.setAttribute("Categories", CategoryDAO.getAllCategory());
                request.setAttribute("Products", ProductDAO.getAllProduct());
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
