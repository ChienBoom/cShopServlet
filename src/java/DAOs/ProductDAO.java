/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Connection.ConnectionUtil;
import Models.Category;
import Models.Product;
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
public class ProductDAO {

    public static List<Product> getAllProduct() throws SQLException, ClassNotFoundException {

        String sql = "Select pro.*, cate.name as cateName, cate.pictureUrl as catePictureUrl, cate.numberProduct as cateNumberProduct, cate.description as cateDescription, cate.isDelete as cateIsDelete from dboProduct as pro"
                + " join dboCategory as cate on pro.categoryId = cate.Id"
                + " where pro.isDelete = 0 and cate.isDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Product> products = new ArrayList<Product>();
        while (rs.next()) {
            Product product = new Product(rs.getLong("id"), rs.getString("name"), rs.getString("pictureUrl"), rs.getInt("quantitySold"),
                    rs.getInt("quantityStock"), rs.getString("description"), rs.getLong("categoryId"), rs.getBoolean("isDelete"));
            Category category = new Category(rs.getLong("categoryId"), rs.getString("cateName"),
                    rs.getString("catePictureUrl"), rs.getInt("cateNumberProduct"), rs.getString("cateDescription"), rs.getBoolean("cateIsDelete"));
            product.setCategory(category);
            products.add(product);
        }
        return products;
    }

    public static List<Product> getProductsByCategoryId(long categoryId) throws SQLException, ClassNotFoundException {

        String sql = "Select pro.*, cate.name as cateName, cate.pictureUrl as catePictureUrl, cate.numberProduct as cateNumberProduct, cate.description as cateDescription, cate.isDelete as cateIsDelete from dboProduct as pro"
                + " join dboCategory as cate on pro.categoryId = cate.Id"
                + " where pro.categoryId = ? and pro.isDelete = 0 and cate.isDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setLong(1, categoryId);
        ResultSet rs = pstm.executeQuery();
        List<Product> products = new ArrayList<Product>();
        while (rs.next()) {
            Product product = new Product(rs.getLong("id"), rs.getString("name"), rs.getString("pictureUrl"), rs.getInt("quantitySold"),
                    rs.getInt("quantityStock"), rs.getString("description"), rs.getLong("categoryId"), rs.getBoolean("isDelete"));
            Category category = new Category(rs.getLong("categoryId"), rs.getString("cateName"),
                    rs.getString("catePictureUrl"), rs.getInt("cateNumberProduct"), rs.getString("cateDescription"), rs.getBoolean("cateIsDelete"));
            product.setCategory(category);
            products.add(product);
        }
        return products;
    }

    public static boolean addProduct(Product product) throws SQLException, ClassNotFoundException {

        String sql = "insert into dboProduct(name, pictureUrl, quantitySold, quantityStock, description, categoryId, isDelete) " //
                + " values (?, ?, ?, ?, ?, ?, ?)";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, product.getName());
        pstm.setString(2, product.getPictureUrl());
        pstm.setInt(3, product.getQuantitySold());
        pstm.setInt(4, product.getQuantityStock());
        pstm.setString(5, product.getDescription());
        pstm.setLong(6, product.getCategoryId());
        pstm.setBoolean(7, false);

        int rowsInserted = pstm.executeUpdate();

        return rowsInserted > 0;
    }

    public static List<Product> searchProduct(String searchInput, long searchCategoryId) throws SQLException, ClassNotFoundException {
        String sql;
        if (searchCategoryId == 999) {
            sql = "Select pro.*, cate.name as cateName, cate.pictureUrl as catePictureUrl, cate.numberProduct as cateNumberProduct, cate.description as cateDescription, cate.isDelete as cateIsDelete from dboProduct as pro" //
                    + " join dboCategory as cate on pro.categoryId = cate.Id"
                    + " where pro.name LIKE ? and pro.isDelete = 0";
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + searchInput + "%");
            ResultSet rs = pstm.executeQuery();
            List<Product> products = new ArrayList<Product>();
            while (rs.next()) {
                Product product = new Product(rs.getLong("id"), rs.getString("name"), rs.getString("pictureUrl"), rs.getInt("quantitySold"),
                        rs.getInt("quantityStock"), rs.getString("description"), rs.getLong("categoryId"), rs.getBoolean("isDelete"));
                Category category = new Category(rs.getLong("categoryId"), rs.getString("cateName"),
                        rs.getString("catePictureUrl"), rs.getInt("cateNumberProduct"), rs.getString("cateDescription"), rs.getBoolean("cateIsDelete"));
                product.setCategory(category);
                products.add(product);
            }
            return products;
        } else {
            sql = "Select pro.*, cate.name as cateName, cate.pictureUrl as catePictureUrl, cate.numberProduct as cateNumberProduct, cate.description as cateDescription, cate.isDelete as cateIsDelete from dboProduct as pro" //
                    + " join dboCategory as cate on pro.categoryId = cate.Id"
                    + " where pro.name LIKE ? and categoryId = ? and pro.isDelete = 0";
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + searchInput + "%");
            pstm.setLong(2, searchCategoryId);
            ResultSet rs = pstm.executeQuery();
            List<Product> products = new ArrayList<Product>();
            while (rs.next()) {
                Product product = new Product(rs.getLong("id"), rs.getString("name"), rs.getString("pictureUrl"), rs.getInt("quantitySold"),
                        rs.getInt("quantityStock"), rs.getString("description"), rs.getLong("categoryId"), rs.getBoolean("isDelete"));
                Category category = new Category(rs.getLong("categoryId"), rs.getString("cateName"),
                        rs.getString("catePictureUrl"), rs.getInt("cateNumberProduct"), rs.getString("cateDescription"), rs.getBoolean("cateIsDelete"));
                product.setCategory(category);
                products.add(product);
            }
            return products;
        }
    }

    public static boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {

        if (product.getPictureUrl().equals("NULL")) {
            String sql = "update dboProduct" //
                    + " set name = ?, quantitySold = ?, quantityStock = ?, description = ?, categoryId = ?" //
                    + " where id = ?";
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, product.getName());
            pstm.setInt(2, product.getQuantitySold());
            pstm.setInt(3, product.getQuantityStock());
            pstm.setString(4, product.getDescription());
            pstm.setLong(5, product.getCategoryId());
            pstm.setLong(6, product.getId());

            int rowsInserted = pstm.executeUpdate();

            return rowsInserted > 0;
        } else {
            String sql = "update dboCategory" //
                    + " set name = ?, pictureUrl = ?, quantitySold = ?, quantityStock = ?, categoryId = ?" //
                    + " where id = ?";
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, product.getName());
            pstm.setString(2, product.getPictureUrl());
            pstm.setInt(3, product.getQuantitySold());
            pstm.setInt(4, product.getQuantityStock());
            pstm.setLong(5, product.getId());
            pstm.setLong(6, product.getId());

            int rowsInserted = pstm.executeUpdate();

            return rowsInserted > 0;
        }
    }

    public static boolean deleteProduct(long id) throws SQLException, ClassNotFoundException {

        String sql = "update dboProduct" //
                + " set IsDelete = 1" //
                + " where Id = ? and IsDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setLong(1, id);
        int rowsUpdated = pstm.executeUpdate();
        return rowsUpdated > 0;
    }
}
