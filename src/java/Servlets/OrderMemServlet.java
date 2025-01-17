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
import Models.Order;
import Models.OrderDetail;
import Models.ProductDetail;
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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhChien
 */
@MultipartConfig()
@WebServlet(urlPatterns = {"/order"})
public class OrderMemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public OrderMemServlet() {
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
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/order.jsp");
            }
            User user = UserDAO.getUserByUsername(userNameGlo);
            request.setAttribute("Orders", OrderDAO.getOrderByUserId(user.getId()));
            request.setAttribute("searchStartDate", LocalDate.now().minusMonths(1));
            request.setAttribute("searchEndDate", LocalDate.now());
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
            RequestDispatcher dispatcher;
            if (roleUser == null || roleUser.equals("ADMIN")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/order.jsp");
            }
            switch (action) {
                case "ADD":
                    String addUsername = request.getParameter("orderUsername");
                    BigDecimal addTotalPrice = BigDecimal.valueOf(Double.parseDouble(request.getParameter("orderTotalPrice")));
                    String addDescription = request.getParameter("orderDescription").trim();
                    int addQuantity = Integer.parseInt(request.getParameter("orderQuantity"));
                    BigDecimal addPrice = BigDecimal.valueOf(Double.parseDouble(request.getParameter("orderPrice")) * addQuantity);
                    long addProductDetailId = Long.parseLong(request.getParameter("orderProductDetailId"));
                    User addUser = UserDAO.getUserByUsername(addUsername);
                    Order addOrder = new Order(1, LocalDateTime.now(), addTotalPrice, addDescription, addUser.getId(), false, "PENDDING");
                    OrderDetail addOrderDetail = new OrderDetail(1, 1, addProductDetailId, addQuantity, addPrice, "");
                    long deleteAddToCart = Long.parseLong(request.getParameter("orderAddToCartId"));
                    boolean addOrderRs = OrderDAO.orderProduct(addOrder, addOrderDetail);
                    boolean deleteAddToCartRs = AddToCartDAO.deleteAddToCart(deleteAddToCart);
                    if (addOrderRs) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Mua hàng thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Mua hàng thất bại");
                    }
                    request.setAttribute("Orders", OrderDAO.getOrderByUserId(addUser.getId()));
                    request.setAttribute("User", UserDAO.getUserByUsername(userNameGlo));
                    break;
                case "SEARCH":
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    Date searchStartDateInput = dateFormat.parse(request.getParameter("searchStartDateInput"));
                    Date searchEndDateInput = dateFormat.parse(request.getParameter("searchEndDateInput"));
                    //cong 1 ngay cho enđate
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(searchEndDateInput);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    Date newSearchEndDateInput = calendar.getTime();

                    request.setAttribute("Orders", OrderDAO.searchOrder(new java.sql.Date(searchStartDateInput.getTime()), new java.sql.Date(newSearchEndDateInput.getTime())));
                    request.setAttribute("searchStartDate", LocalDate.parse(request.getParameter("searchStartDateInput"), formatter));
                    request.setAttribute("searchEndDate", LocalDate.parse(request.getParameter("searchEndDateInput"), formatter));
                    break;
                case "CANCEL":
                    long cancelOrderId = Long.parseLong(request.getParameter("cancelOrderId"));
                    boolean cancelOrderRs = OrderDAO.cancelOrder(cancelOrderId);
                    if (cancelOrderRs) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Huỷ đơn hàng thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Huỷ đơn hàng thất bại");
                    }
                    User cancelUser = UserDAO.getUserByUsername(userNameGlo);
                    request.setAttribute("Orders", OrderDAO.getOrderByUserId(cancelUser.getId()));
                    request.setAttribute("searchStartDate", LocalDate.now().minusMonths(1));
                    request.setAttribute("searchEndDate", LocalDate.now());
                    break;
                case "RE-ORDER":
                    long reOrderId = Long.parseLong(request.getParameter("reOrderId"));
                    boolean reOrderRs = OrderDAO.reOrder(reOrderId);
                    if (reOrderRs) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Đặt lại đơn hàng thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Đặt lại đơn hàng thất bại");
                    }
                    User reOrderuser = UserDAO.getUserByUsername(userNameGlo);
                    request.setAttribute("Orders", OrderDAO.getOrderByUserId(reOrderuser.getId()));
                    request.setAttribute("searchStartDate", LocalDate.now().minusMonths(1));
                    request.setAttribute("searchEndDate", LocalDate.now());
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
