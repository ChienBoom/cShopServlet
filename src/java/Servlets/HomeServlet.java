/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.AccountDAO;
import DAOs.CategoryDAO;
import DAOs.ProductDAO;
import DAOs.ProductDetailDAO;
import Models.Account;
import Models.ProductDetail;
import Services.UploadService;
import Utils.MyUtils;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhChien
 */
@WebServlet(urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public HomeServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String roleUser = (String) getServletContext().getAttribute("ROLE");
        try {
            if (roleUser == null) {
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/home.jsp");
                request.setAttribute("Categories", CategoryDAO.getAllCategory());
                request.setAttribute("Products", ProductDAO.getAllProduct());
                request.setAttribute("SEARCH", "FALSE");
                dispatcher.forward(request, response);
            } else {
                String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
                if (roleUser.equals("ADMIN")) {
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/home.jsp");
                    request.setAttribute("USERNAME", userNameGlo);
                    dispatcher.forward(request, response);
                } else {
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/home.jsp");
                    request.setAttribute("USERNAME", userNameGlo);
                    request.setAttribute("Categories", CategoryDAO.getAllCategory());
                    request.setAttribute("Products", ProductDAO.getAllProduct());
                    request.setAttribute("SEARCH", "FALSE");
                    dispatcher.forward(request, response);
                }
            }
        } catch (Exception e) {
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
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/home.jsp");
            } else if (roleUser.equals("ADMIN")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/home.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/home.jsp");
            }

            switch (action) {
                case "SEARCH":
                    String searchInput = request.getParameter("searchInput").trim();
                    request.setAttribute("Products", ProductDAO.searchProduct(searchInput, 999));
                    request.setAttribute("searchInput", searchInput);
                    if (searchInput.equals("")) {
                        request.setAttribute("Categories", CategoryDAO.getAllCategory());
                        request.setAttribute("SEARCH", "FALSE");
                    } else {
                        request.setAttribute("SEARCH", "TRUE");
                    }
                    break;
                case "SHOW-PRODUCT-CATEGORY":
                    long showProCateId = Long.parseLong(request.getParameter("showProCateId"));
                    request.setAttribute("Categories", CategoryDAO.getAllCategory());
                    request.setAttribute("Products", ProductDAO.getProductsByCategoryId(showProCateId));
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
