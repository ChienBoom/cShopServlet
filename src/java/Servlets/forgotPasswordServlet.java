/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.AccountDAO;
import DAOs.UserDAO;
import Models.Account;
import Models.User;
import Utils.SendEmailUtil;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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
@WebServlet(urlPatterns = {"/forgotPassword"})
public class forgotPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public forgotPasswordServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/forgotPassword.jsp");
        dispatcher.forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        Account account;
        try {
            account = AccountDAO.getAccountByUsername(username);
            if (account == null) {
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/forgotPassword.jsp");
                request.setAttribute("STATUS", "ERROR");
                request.setAttribute("MESSAGE", "Tài khoản không tồn tại!");
                dispatcher.forward(request, response);
            } else {
                getServletContext().setAttribute("ROLE", account.getRole());
                getServletContext().setAttribute("USERNAME", account.getUsername());
                SendEmailUtil sendEmailUtil = new SendEmailUtil();
                User user = UserDAO.getUserByUsername(account.getUsername());
                sendEmailUtil.sendEmail("virusss1241@gmail.com", account.getPassword());
//                sendEmailUtil.sendEmail(user.getEmail(), account.getPassword());
//                session.setAttribute("STATUS", "SUCCESS");
//                session.setAttribute("MESSAGE", "Đã gửi tin nhắn về Email của bạn!");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
