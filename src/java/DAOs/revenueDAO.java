/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Connection.ConnectionUtil;
import Models.Account;
import Models.Revenue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MinhChien
 */
public class revenueDAO {
    public static List<Revenue> revenueByMonth(String year) throws ClassNotFoundException, SQLException{
        String sql = "select MONTH(orderAt) as month, SUM(totalPrice) as totalPrice"
                + " from dboOrder"
                + " where YEAR(orderAt) = ? and isDelete = 0"
                + " group by MONTH(orderAt)"
                + " order by month";
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, year);
        ResultSet rs = pstm.executeQuery();

        List<Revenue> revenues = new ArrayList<Revenue>();
        while (rs.next()) {
            Revenue revenue = new Revenue(rs.getString("month"), rs.getBigDecimal("totalPrice"));
            revenues.add(revenue);
        }
        return revenues;
    }
    
    public static List<Revenue> revenueByCategory(String year) throws ClassNotFoundException, SQLException{
        String sql = "select cate.name as cateName, SUM(ord.totalPrice) as totalPrice"
                + " from dboOrder as ord"
                + " join dboOrderDetail as ordde on ord.Id = ordde.orderId"
                + " join dboProductDetail as prod on ordde.productDetailId = prod.id"
                + " join dboProduct as pro on prod.productId = pro.id"
                + " join dboCategory as cate on pro.categoryId = cate.id"
                + " where YEAR(ord.orderAt) = ? and ord.isDelete = 0"
                + " group by cate.Name"
                + " order by cateName";
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, year);
        ResultSet rs = pstm.executeQuery();

        List<Revenue> revenues = new ArrayList<Revenue>();
        while (rs.next()) {
            Revenue revenue = new Revenue(rs.getString("cateName"), rs.getBigDecimal("totalPrice"));
            revenues.add(revenue);
        }
        return revenues;
    }
    
    public static List<Revenue> revenueByProduct(String year) throws ClassNotFoundException, SQLException{
        String sql = "select pro.name as proName, SUM(ord.totalPrice) as totalPrice"
                + " from dboOrder as ord"
                + " join dboOrderDetail as ordde on ord.Id = ordde.orderId"
                + " join dboProductDetail as prod on ordde.productDetailId = prod.id"
                + " join dboProduct as pro on prod.productId = pro.id"
                + " where YEAR(ord.orderAt) = ? and ord.isDelete = 0"
                + " group by pro.Name"
                + " order by proName";
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, year);
        ResultSet rs = pstm.executeQuery();

        List<Revenue> revenues = new ArrayList<Revenue>();
        while (rs.next()) {
            Revenue revenue = new Revenue(rs.getString("proName"), rs.getBigDecimal("totalPrice"));
            revenues.add(revenue);
        }
        return revenues;
    }
    
}
