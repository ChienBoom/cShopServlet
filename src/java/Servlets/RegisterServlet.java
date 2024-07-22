/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.AccountDAO;
import DAOs.UserDAO;
import Models.Account;
import Models.User;
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
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhChien
 */
@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            boolean accountIsExis = AccountDAO.findAccountByUsername(username);
            if (accountIsExis) {
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/register.jsp");
                request.setAttribute("STATUS", "USERNAME-EXIS");
                request.setAttribute("MESSAGE", "Tên tài khoản đã tồn tại");
                dispatcher.forward(request, response);
            } else {
                boolean createAccount = AccountDAO.registerAccount(username, password);
                if (createAccount) {
                    session.setAttribute("ROLE", "MEMBER");
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/home.jsp");
                    getServletContext().setAttribute("ROLE", "MEMBER");
                    getServletContext().setAttribute("USERNAME", username);
                    request.setAttribute("MESSAGE", "Đăng ký tài khoản thành công");
                    response.sendRedirect(request.getContextPath() + "/userNotifi");
                } else {
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/register.jsp");
                    request.setAttribute("STATUS", "ERROR");
                    request.setAttribute("MESSAGE", "Đăng ký tài khoản thất bại");
                    dispatcher.forward(request, response);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/error.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
