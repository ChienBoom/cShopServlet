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
@WebServlet(urlPatterns = {"/product/*"})
public class ProductMemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ProductMemServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
        String roleUser = (String) getServletContext().getAttribute("ROLE");
        String pathInfo = request.getPathInfo();
        try {
            String[] pathParts = pathInfo.split("/");
            RequestDispatcher dispatcher;
            if (pathParts.length < 1) {
                if (roleUser == null) {
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/products.jsp");

                } else {
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/products.jsp");
                }
                request.setAttribute("Products", ProductDAO.getAllProduct());
                request.setAttribute("Categories", CategoryDAO.getAllCategory());
                request.setAttribute("searchCategoryId", 999);
            } else {
                String categoryId = pathParts[1];

                if (roleUser == null) {
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/products.jsp");
                } else {
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/products.jsp");
                }
                request.setAttribute("Products", ProductDAO.getProductsByCategoryId(Long.parseLong(categoryId)));
                request.setAttribute("Categories", CategoryDAO.getAllCategory());
                request.setAttribute("searchCategoryId", categoryId);
            }
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
            String action = request.getParameter("action");
            RequestDispatcher dispatcher;
            if (roleUser == null) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/products.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/products.jsp");
            }
            switch (action) {
                case "SEARCH":
                    String searchInput = request.getParameter("searchInput").trim();
                    long searchCategoryIdInput = Long.parseLong(request.getParameter("searchCategoryIdInput"));
                    request.setAttribute("Products", ProductDAO.searchProduct(searchInput, searchCategoryIdInput));
                    request.setAttribute("Categories", CategoryDAO.getAllCategory());
                    request.setAttribute("searchInput", searchInput);
                    request.setAttribute("searchCategoryId", searchCategoryIdInput);
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
