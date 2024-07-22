/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.CategoryDAO;
import DAOs.ProductDAO;
import DAOs.ProductDetailDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhChien
 */
@MultipartConfig()
@WebServlet(urlPatterns = {"/productDetail/*"})
public class ProductDetailMemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ProductDetailMemServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
        String roleUser = (String) getServletContext().getAttribute("ROLE");
        String pathInfo = request.getPathInfo();
        HttpSession session = request.getSession();
        try {
            String[] pathParts = pathInfo.split("/");
            RequestDispatcher dispatcher;
            if (pathParts.length < 1) {
                if (roleUser == null) {
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/productDetail.jsp");

                } else {
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/productDetail.jsp");
                }
                request.setAttribute("ProductDetails", ProductDetailDAO.getAllProductDetail());
            } else {
                String productId = pathParts[1];
                if (roleUser == null) {
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/productDetail.jsp");
                } else {
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/productDetail.jsp");
                    String status = (String) session.getAttribute("STATUS");
                    String message = (String) session.getAttribute("MESSAGE");
                    request.setAttribute("STATUS", status);
                    request.setAttribute("MESSAGE", message);
                    session.removeAttribute("STATUS");
                    session.removeAttribute("MESSAGE");
                }
                request.setAttribute("Product", ProductDAO.getProductById(Long.parseLong(productId)));
                request.setAttribute("ProductDetails", ProductDetailDAO.getProductDetailsByProductId(Long.parseLong(productId)));
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
            RequestDispatcher dispatcher;
            if (roleUser == null) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/productDetail.jsp");
            }
            else if(roleUser.equals("ADMIN")){
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            }
            else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/productDetail.jsp");
            }
            String action = request.getParameter("action");
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
