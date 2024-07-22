/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.AccountDAO;
import DAOs.CategoryDAO;
import DAOs.UserDAO;

import Models.Category;
import Services.UploadService;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhChien
 */
@MultipartConfig()
@WebServlet(urlPatterns = {"/categoryManagement"})
public class CategoryManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CategoryManagementServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
        String roleUser = (String) getServletContext().getAttribute("ROLE");
        try {
            RequestDispatcher dispatcher;
            if(roleUser == null || roleUser.equals("MEMBER")){
                dispatcher  = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            }
            else{
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/categoryManagement.jsp");
            }
            request.setAttribute("Categories", CategoryDAO.getAllCategory());
            request.setAttribute("USERNAME", userNameGlo);
            request.setAttribute("searchInput", "");
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
            String action = request.getParameter("action");
            RequestDispatcher dispatcher;
            if(roleUser == null || roleUser.equals("MEMBER")){
                dispatcher  = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            }
            else{
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/categoryManagement.jsp");
            }
            switch (action) {
                case "ADD":
                    // Lấy thông tin file
                    Part picture = request.getPart("addPicUrlCate");
                    UploadService uploadService = new UploadService();
                    String fileName = uploadService.UploadPicture(request, picture);

                    Category category = new Category(1, request.getParameter("addCateName"), fileName,
                            Integer.parseInt(request.getParameter("addNumProCate")), request.getParameter("addDescCate").trim(), false);
                    boolean addCate = CategoryDAO.addCategory(category);
                    request.setAttribute("Categories", CategoryDAO.getAllCategory());
                    request.setAttribute("searchInput", "");
                    if (addCate) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Thêm mới danh mục thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Thêm mới danh mục thất bại");
                    }
                    break;
                case "EDIT":
                    // Lấy thông tin file
                    Part editPicture = request.getPart("editPicUrlCate");
                    String editFileName = "NULL";
                    if (editPicture.getSubmittedFileName() != null && !editPicture.getSubmittedFileName().isEmpty()) {
                        UploadService editUploadService = new UploadService();
                        editFileName = editUploadService.UploadPicture(request, editPicture);
                    }
                    Category editCategory = new Category(Long.parseLong(request.getParameter("editCateId")), request.getParameter("editCateName"), editFileName,
                            Integer.parseInt(request.getParameter("editNumProCate")), request.getParameter("editDescCate").trim(), false);
                    boolean editCate = CategoryDAO.updateCategory(editCategory);
                    request.setAttribute("Categories", CategoryDAO.getAllCategory());
                    request.setAttribute("searchInput", "");
                    if (editCate) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Sửa thông tin danh mục thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Sửa thông tin danh mục thất bại");
                    }
                    break;
                case "SEARCH":
                    String searchInput = request.getParameter("searchCategoryInput").trim();
                    request.setAttribute("Categories", CategoryDAO.searchCategory(searchInput));
                    request.setAttribute("searchInput", searchInput);
                    break;

                case "DELETE":
                    long deleteCategoryId;
                    boolean deleteCategory = false;
                    String deleteCategoryIdPara = request.getParameter("deleteCategoryId");
                    deleteCategoryId = Long.parseLong(deleteCategoryIdPara);
                    deleteCategory = CategoryDAO.deleteCategory(deleteCategoryId);
                    request.setAttribute("Categories", CategoryDAO.getAllCategory());
                    request.setAttribute("searchInput", "");
                    if (deleteCategory) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Xoá danh mục thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Xoá danh mục thất bại");
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
