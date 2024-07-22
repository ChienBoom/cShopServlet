/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.AccountDAO;
import DAOs.UserDAO;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhChien
 */
@WebServlet(urlPatterns = {"/memberManagement"})
public class MemberManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public MemberManagementServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
        String roleUser = (String) getServletContext().getAttribute("ROLE");
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            RequestDispatcher dispatcher;
            if (roleUser == null || roleUser.equals("MEMBER")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/memberManagement.jsp");
            }
            List<User> users = UserDAO.findUserMember();
            request.setAttribute("Users", users);
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
            HttpSession session = request.getSession();
            session.invalidate();
            RequestDispatcher dispatcher;
            if (roleUser == null || roleUser.equals("MEMBER")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/memberManagement.jsp");
            }
            String action = request.getParameter("action");
            switch (action) {
                case "BAN":
                    long banAccountId;
                    boolean banAccount = false;
                    String banAccountIdPara = request.getParameter("banAccountId");
                    banAccountId = Long.parseLong(banAccountIdPara);
                    banAccount = AccountDAO.banAccount(banAccountId);
                    request.setAttribute("Users", UserDAO.findUserMember());
                    if (banAccount) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Khoá tài khoản thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Khoá tài khoản thất bại");
                    }
                    break;
                case "UN-BAN":
                    long unBanAccountId;
                    boolean unBanAccount = false;
                    String unBanAccountIdPara = request.getParameter("unBanAccountId");
                    unBanAccountId = Long.parseLong(unBanAccountIdPara);
                    unBanAccount = AccountDAO.unBanAccount(unBanAccountId);
                    request.setAttribute("Users", UserDAO.findUserMember());
                    if (unBanAccount) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Mở khoá tài khoản thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Mở khoá tài khoản thất bại");
                    }
                    break;

                case "DELETE":
                    long deleteAccountId;
                    boolean deleteAccount = false;
                    String deleteAccountIdPara = request.getParameter("deleteAccountId");
                    deleteAccountId = Long.parseLong(deleteAccountIdPara);
                    deleteAccount = AccountDAO.deleteAccount(deleteAccountId);
                    request.setAttribute("Users", UserDAO.findUserMember());
                    if (deleteAccount) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Mở khoá tài khoản thành công");
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Mở khoá tài khoản thất bại");
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
