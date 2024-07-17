/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.CategoryDAO;
import DAOs.ProductDAO;
import DAOs.UserDAO;
import Models.Product;
import Models.User;
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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
        String roleUser = (String) getServletContext().getAttribute("ROLE");
        try {
            RequestDispatcher dispatcher;
            if (roleUser == null) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            } else if (roleUser.equals("ADMIN")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/profile.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/profile.jsp");
            }
            request.setAttribute("User", UserDAO.getUserByUsername(userNameGlo));
            request.setAttribute("USERNAME", userNameGlo);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/profile.jsp");
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
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/profile.jsp");
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
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    // Lấy thông tin file
                    Part editPicture = request.getPart("editUserPicture");
                    String editFileName = "NULL";
                    if (editPicture.getSubmittedFileName() != null && !editPicture.getSubmittedFileName().isEmpty()) {
                        UploadService editUploadService = new UploadService();
                        editFileName = editUploadService.UploadPicture(request, editPicture);
                    }
                    User editUser = new User(1, request.getParameter("editFullName"), request.getParameter("editEmail"), request.getParameter("editSex"),
                            request.getParameter("editAddress"), editFileName, dateFormat.parse(request.getParameter("editDOB")), 1);
                    editUser.setUsername(request.getParameter("editUsername"));
                    boolean editUs = UserDAO.updateProfile(editUser);

                    if (editUs) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Cập nhật thông tin thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Cập nhật thông tin thất bại");
                    }
                    break;
                case "CHANGE-AVATAR":
                    Part changePicture = request.getPart("changePicture");
                    UploadService changeUploadService = new UploadService();
                    String changeFileName = changeUploadService.UploadPicture(request, changePicture);
                    User changeUser = new User();
                    System.out.println("username: " + request.getParameter("changeUsername"));
                    changeUser.setUsername(request.getParameter("changeUsername"));
                    changeUser.setPictureUrl(changeFileName);
                    boolean changeUs = UserDAO.changeAvatar(changeUser);
                    if (changeUs) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Cập nhật ảnh đại diện thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Cập nhật ảnh đại diện thất bại");
                    }
                    break;
                default:
                    break;
            }
            request.setAttribute("User", UserDAO.getUserByUsername(userNameGlo));
            request.setAttribute("USERNAME", userNameGlo);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            try {
                System.out.println(e);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/profile.jsp");
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
