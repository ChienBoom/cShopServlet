/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.UserDAO;
import Models.User;
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
import java.text.SimpleDateFormat;

/**
 *
 * @author MinhChien
 */
@MultipartConfig()
@WebServlet(urlPatterns = {"/userNotifi"})
public class UserNotifiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UserNotifiServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
        String roleUser = (String) getServletContext().getAttribute("ROLE");
        try {
            RequestDispatcher dispatcher;
            if (roleUser == null) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            } else if (roleUser.equals("ADMIN")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/userNotifi.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/userNotifi.jsp");
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
            if(roleUser == null){
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            }
            else if(roleUser.equals("MEMBER")) dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/memberViews/profile.jsp");
            else{
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/profile.jsp");
            }
            String action = request.getParameter("action");
             
            switch (action) {
                case "INSERT":
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Part editPicture = request.getPart("insUserPicture");
                    String editFileName = "NULL";
                    if (editPicture.getSubmittedFileName() != null && !editPicture.getSubmittedFileName().isEmpty()) {
                        UploadService editUploadService = new UploadService();
                        editFileName = editUploadService.UploadPicture(request, editPicture);
                    }
                    User editUser = new User(1, request.getParameter("insFullName"), request.getParameter("insEmail"), request.getParameter("insSex"),
                            request.getParameter("insAddress"), editFileName, dateFormat.parse(request.getParameter("insDOB")), 1);
                    editUser.setUsername(request.getParameter("insUsername"));
                    boolean insUs = UserDAO.insertProfile(editUser);
                    if (insUs) {
                        request.setAttribute("STATUS", "SUCCESS");
                        request.setAttribute("MESSAGE", "Cập nhật thông tin thành công");
                        response.sendRedirect(request.getContextPath() + "/home");
                        return;
                    } else {
                        request.setAttribute("STATUS", "ERROR");
                        request.setAttribute("MESSAGE", "Cập nhật thông tin thất bại");
                    }
                    break;
                default:
                    break;
            }
            request.setAttribute("User", UserDAO.getUserByUsername(userNameGlo));
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
