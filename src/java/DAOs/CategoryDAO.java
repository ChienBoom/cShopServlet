/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Connection.ConnectionUtil;
import Models.Category;
import Models.User;
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
public class CategoryDAO {

    public static List<Category> getAllCategory() throws SQLException, ClassNotFoundException {

        String sql = "Select * from dboCategory where isDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Category> categories = new ArrayList<Category>();
        while (rs.next()) {
            Category category = new Category(rs.getLong("Id"), rs.getString("name"), rs.getString("pictureUrl"), rs.getInt("numberProduct"),
                    rs.getString("description"), rs.getBoolean("isDelete"));
            categories.add(category);
        }
        return categories;
    }
    
    public static List<Category> searchCategory(String searchInput) throws SQLException, ClassNotFoundException {

        String sql = "Select * from dboCategory where name LIKE ? and isDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
         pstm.setString(1, "%" + searchInput + "%");
        ResultSet rs = pstm.executeQuery();
        List<Category> categories = new ArrayList<Category>();
        while (rs.next()) {
            Category category = new Category(rs.getLong("Id"), rs.getString("name"), rs.getString("pictureUrl"), rs.getInt("numberProduct"),
                    rs.getString("description"), rs.getBoolean("isDelete"));
            categories.add(category);
        }
        return categories;
    }

    public static boolean addCategory(Category category) throws SQLException, ClassNotFoundException {

        String sql = "insert into dboCategory(name, pictureUrl, numberProduct, description, isDelete) " //
                + " values (?, ?, ?, ?, ?)";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, category.getName());
        pstm.setString(2, category.getPictureUrl());
        pstm.setInt(3, category.getNumberProduct());
        pstm.setString(4, category.getDescription());
        pstm.setBoolean(5, false);

        int rowsInserted = pstm.executeUpdate();

        return rowsInserted > 0;
    }

    public static boolean updateCategory(Category category) throws SQLException, ClassNotFoundException {

        if (category.getPictureUrl().equals("NULL")) {
            String sql = "update dboCategory" //
                    + " set name = ?, numberProduct = ?, description = ?" //
                    + " where id = ?";
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, category.getName());
            pstm.setInt(2, category.getNumberProduct());
            pstm.setString(3, category.getDescription());
            pstm.setLong(4, category.getId());

            int rowsInserted = pstm.executeUpdate();

            return rowsInserted > 0;
        } else {
            String sql = "update dboCategory" //
                    + " set name = ?, pictureUrl = ?, numberProduct = ?, description = ?" //
                    + " where id = ?";
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, category.getName());
            pstm.setString(2, category.getPictureUrl());
            pstm.setInt(3, category.getNumberProduct());
            pstm.setString(4, category.getDescription());
            pstm.setLong(5, category.getId());

            int rowsInserted = pstm.executeUpdate();

            return rowsInserted > 0;
        }
    }

    public static boolean deleteCategory(long id) throws SQLException, ClassNotFoundException {

        String sql = "update dboCategory" //
                + " set IsDelete = 1" //
                + " where Id = ? and IsDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setLong(1, id);
        int rowsUpdated = pstm.executeUpdate();
        return rowsUpdated > 0;
    }

}
