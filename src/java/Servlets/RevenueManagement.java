/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAOs.CategoryDAO;
import DAOs.ProductDAO;
import DAOs.revenueDAO;
import Models.Revenue;
import Models.RevenueData;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author MinhChien
 */
@MultipartConfig()
@WebServlet(urlPatterns = {"/revenue"})
public class RevenueManagement extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public RevenueManagement() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userNameGlo = (String) getServletContext().getAttribute("USERNAME");
            String roleUser = (String) getServletContext().getAttribute("ROLE");
            RequestDispatcher dispatcher;
            if (roleUser == null || roleUser.equals("MEMBER")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/revenueManagement.jsp");
            }
            List<Revenue> revenues = revenueDAO.revenueByMonth("2024");
            RevenueData revenueData = new RevenueData();
            List<String> labels = new ArrayList<>();
            List<BigDecimal> totalPrices = new ArrayList<>();
            for (Revenue item : revenues) {
                labels.add(item.getLabel());
                totalPrices.add(item.getValue());
            }
            revenueData.setLabels(labels);
            revenueData.setData(totalPrices);
            Gson gson = new Gson();
            String data = gson.toJson(revenueData);
            request.setAttribute("Data", data.toString());
            request.setAttribute("OptionYearSelected", "2024");
            request.setAttribute("OptionChartSelected", "bar");
            request.setAttribute("OptionTypeSelected", "MONTH");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/error.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            System.out.println(e);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/error.jsp");
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
            if (roleUser == null || roleUser.equals("MEMBER")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/commonViews/notfound.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/adminViews/revenueManagement.jsp");
            }
            switch (action) {
                case "CHANGE-OPTIONS":
                    String optionType = request.getParameter("revenueOptionType");
                    String optionYear = request.getParameter("revenueOptionYear");
                    String optionChart = request.getParameter("revenueOptionChart");
                    List<Revenue> revenues;
                    if (optionType.equals("MONTH")) {
                        revenues = revenueDAO.revenueByMonth(optionYear);
                    } else if (optionType.equals("CATEGORY")) {
                        revenues = revenueDAO.revenueByCategory(optionYear);
                    } else {
                        revenues = revenueDAO.revenueByProduct(optionYear);
                    }
                    RevenueData revenueData = new RevenueData();
                    List<String> labels = new ArrayList<>();
                    List<BigDecimal> totalPrices = new ArrayList<>();
                    for (Revenue item : revenues) {
                        labels.add(item.getLabel());
                        totalPrices.add(item.getValue());
                    }
                    revenueData.setLabels(labels);
                    revenueData.setData(totalPrices);
                    Gson gson = new Gson();
                    String data = gson.toJson(revenueData);
                    request.setAttribute("Data", data.toString());
                    request.setAttribute("OptionYearSelected", optionYear);
                    request.setAttribute("OptionChartSelected", optionChart);
                    request.setAttribute("OptionTypeSelected", optionType);
                    break;

                case "EXPORT-EXCEL":
                    String exOptionType = request.getParameter("exOptionType");
                    String exOptionYear = request.getParameter("exOptionYear");
                    String exOptionChart = request.getParameter("exOptionChart");
                    List<Revenue> exRevenues;
                    if (exOptionType.equals("MONTH")) {
                        revenues = revenueDAO.revenueByMonth(exOptionYear);
                    } else if (exOptionType.equals("CATEGORY")) {
                        revenues = revenueDAO.revenueByCategory(exOptionYear);
                    } else {
                        revenues = revenueDAO.revenueByProduct(exOptionYear);
                    }
                    RevenueData exRevenueData = new RevenueData();
                    List<String> exlabels = new ArrayList<>();
                    List<BigDecimal> exTotalPrices = new ArrayList<>();
                    for (Revenue item : revenues) {
                        exlabels.add(item.getLabel());
                        exTotalPrices.add(item.getValue());
                    }
                    exRevenueData.setLabels(exlabels);
                    exRevenueData.setData(exTotalPrices);
                    Gson exGson = new Gson();
                    String exData = exGson.toJson(exRevenueData);

                    //Export
                    // Tạo workbook và sheet
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    Sheet sheet = workbook.createSheet("Data");
                    // Tạo các dòng và ô với dữ liệu mẫu
                    Row headerRow = sheet.createRow(0);
                    Cell headerCell1 = headerRow.createCell(0);
                    headerCell1.setCellValue("ID");

                    Cell headerCell2 = headerRow.createCell(1);
                    headerCell2.setCellValue("Doanh thu");

                    for (int i = 1; i <= exlabels.size(); i++) {
                        Row dataRow = sheet.createRow(i);
                        Cell dataCell1 = dataRow.createCell(0);
                        dataCell1.setCellValue(exlabels.get(i - 1));

                        Cell dataCell2 = dataRow.createCell(1);
                        dataCell2.setCellValue(exTotalPrices.get(i - 1).toString());
                    }

                    // Đặt header cho response để tải file xuống
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=thong_ke_doanh_thu.xlsx");
                    // Ghi workbook ra output stream của response
                    try (OutputStream out = response.getOutputStream()) {
                        workbook.write(out);
                    } finally {
                        workbook.close();
                    }

                    request.setAttribute("Data", exData.toString());
                    request.setAttribute("OptionYearSelected", exOptionYear);
                    request.setAttribute("OptionChartSelected", exOptionChart);
                    request.setAttribute("OptionTypeSelected", exOptionType);
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
