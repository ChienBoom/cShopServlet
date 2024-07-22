/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.AddToCartDAO;
import DAOs.OrderDAO;
import DAOs.ProductDAO;
import DAOs.ProductDetailDAO;
import DAOs.UserDAO;
import Models.AddToCart;
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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhChien
 */
@MultipartConfig()
@WebServlet(urlPatterns = {"/addToCart"})
public class AddToCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddToCartServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
        String roleUser = (String) getServletContext().getAttribute("ROLE");
        try {
            RequestDispatcher dispatcher;
            if (roleUser == null || roleUser.equals("ADMIN")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/addToCart.jsp");
            }
            request.setAttribute("AddToCarts", AddToCartDAO.getAddToCartByUsername(userNameGlo));
            request.setAttribute("User", UserDAO.getUserByUsername(userNameGlo));
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
            HttpSession session = request.getSession();
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/productDetail.jsp");
            switch (action) {
                case "ADD-TO-CART":
                    if (roleUser == null) {
                        response.sendRedirect(request.getContextPath() + "/register");
                        return;
                    } else if (roleUser.equals("ADMIN")) {
                        dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
                    } else {
                        dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/productDetail.jsp");
                        long addProductDetailId = Long.parseLong(request.getParameter("productDetailIdInput"));
                        long addProductId = Long.parseLong(request.getParameter("productIdInput"));
                        int addQuantity = Integer.parseInt(request.getParameter("quantityInput"));
                        AddToCart addToCart = new AddToCart(1, userNameGlo, addProductDetailId, addQuantity);
                        boolean addToCartRs = AddToCartDAO.insertAddToCart(addToCart);
                        if (addToCartRs) {
                            session.setAttribute("STATUS", "SUCCESS");
                            session.setAttribute("MESSAGE", "Thêm sản phẩm vào giỏ hàng thành công");
                        } else {
                            session.setAttribute("STATUS", "ERROR");
                            session.setAttribute("MESSAGE", "Lỗi hệ thống");
                        }
                        response.sendRedirect(request.getContextPath() + "/productDetail/" + addProductId);
                        return;
                    }
                    break;
                case "EDIT":
                    AddToCart editAddToCart = new AddToCart();
                    editAddToCart.setId(Long.parseLong(request.getParameter("editAddToCartId")));
                    editAddToCart.setQuantity(Integer.parseInt(request.getParameter("editQuantity")));
                    boolean editRs = AddToCartDAO.updateAddToCart(editAddToCart);
                    if (editRs) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Cập nhật giỏ hàng thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Cập nhật giỏ hàng thất bại");
                    }
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/addToCart.jsp");
                    request.setAttribute("AddToCarts", AddToCartDAO.getAddToCartByUsername(userNameGlo));
                    request.setAttribute("USERNAME", userNameGlo);
                    break;
                case "DELETE":
                    long deleteAddToCart = Long.parseLong(request.getParameter("deleteAddToCartId"));
                    boolean deleteRs = AddToCartDAO.deleteAddToCart(deleteAddToCart);
                    if (deleteRs) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Xoá khỏi giỏ hàng thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Xoá sản phẩm khỏi giỏ hàng thất bại");
                    }
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/addToCart.jsp");
                    request.setAttribute("AddToCarts", AddToCartDAO.getAddToCartByUsername(userNameGlo));
                    request.setAttribute("USERNAME", userNameGlo);
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
