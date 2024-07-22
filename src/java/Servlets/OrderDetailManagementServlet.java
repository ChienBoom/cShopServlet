/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.OrderDetailDAO;
import DAOs.ProductDAO;
import DAOs.ProductDetailDAO;
import Models.ProductDetail;
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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhChien
 */
@MultipartConfig()
@WebServlet(urlPatterns = {"/orderDetailManagement"})
public class OrderDetailManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public OrderDetailManagementServlet() {
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
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/orderDetailManagement.jsp");
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
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/orderDetailManagement.jsp");
            }
            String action = request.getParameter("action");
            switch (action) {
                case "SEARCH":
                    String searchInput = request.getParameter("searchProductDetailInput").trim();
                    long searchProductIdInput = Long.parseLong(request.getParameter("searchProductIdInput"));
                    request.setAttribute("Products", ProductDAO.getAllProduct());
                    request.setAttribute("ProductDetails", ProductDetailDAO.searchProductDetail(searchInput, searchProductIdInput));
                    request.setAttribute("searchInput", searchInput);
                    request.setAttribute("searchProductId", searchProductIdInput);
                    break;
                case "SHOW-ORDER-DETAIL":
                    long showOrderId = Long.parseLong(request.getParameter("showOrderId"));
                    request.setAttribute("OrderDetails", OrderDetailDAO.getOrderDetailsByOrderId(showOrderId));
                    request.setAttribute("searchInput", "");
                    request.setAttribute("showOrderId", showOrderId);
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
