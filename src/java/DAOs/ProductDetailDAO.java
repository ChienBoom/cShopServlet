/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Connection.ConnectionUtil;
import Models.Product;
import Models.ProductDetail;
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
public class ProductDetailDAO {

    public static List<ProductDetail> getAllProductDetail() throws SQLException, ClassNotFoundException {

        String sql = "Select prod.*, pro.name as proName, pro.pictureUrl as proPictureUrl, pro.quantitySold as proQuantitySold, pro.quantityStock as proQuantityStock, pro.description as proDescription, pro.categoryId as proCategoryId, pro.isDelete as proIsDelete from dboProductDetail as prod" //
                + " join dboProduct as pro on prod.productId = pro.id"//
                + " where pro.isDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
        while (rs.next()) {
            ProductDetail productDetail = new ProductDetail(rs.getLong("id"), rs.getString("size"), rs.getString("color"), rs.getString("pictureUrl"), rs.getBigDecimal("price"),
                    rs.getInt("quantitySold"), rs.getInt("quantityStock"), rs.getString("description"), rs.getLong("productId"));
            Product product = new Product(rs.getLong("productId"), rs.getString("proName"), rs.getString("proPictureUrl"), rs.getInt("proQuantitySold"),
                    rs.getInt("proQuantityStock"), rs.getString("proDescription"), rs.getLong("proCategoryId"), rs.getBoolean("proIsDelete"));
            productDetail.setProduct(product);
            productDetails.add(productDetail);
        }
        return productDetails;
    }

    public static List<ProductDetail> getProductDetailsByProductId(long productId) throws SQLException, ClassNotFoundException {

        String sql = "Select prod.*, pro.name as proName, pro.pictureUrl as proPictureUrl, pro.quantitySold as proQuantitySold, pro.quantityStock as proQuantityStock, pro.description as proDescription, pro.categoryId as proCategoryId, pro.isDelete as proIsDelete from dboProductDetail as prod" //
                + " join dboProduct as pro on prod.productId = pro.id"//
                + " where prod.productId = ? and pro.isDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setLong(1, productId);
        ResultSet rs = pstm.executeQuery();
        List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
        while (rs.next()) {
            ProductDetail productDetail = new ProductDetail(rs.getLong("id"), rs.getString("size"), rs.getString("color"), rs.getString("pictureUrl"), rs.getBigDecimal("price"),
                    rs.getInt("quantitySold"), rs.getInt("quantityStock"), rs.getString("description"), rs.getLong("productId"));
            Product product = new Product(rs.getLong("productId"), rs.getString("proName"), rs.getString("proPictureUrl"), rs.getInt("proQuantitySold"),
                    rs.getInt("proQuantityStock"), rs.getString("proDescription"), rs.getLong("proCategoryId"), rs.getBoolean("proIsDelete"));
            productDetail.setProduct(product);
            productDetails.add(productDetail);
        }
        return productDetails;
    }

    public static boolean addProductDetail(ProductDetail productDetail) throws SQLException, ClassNotFoundException {

        String sql = "insert into dboProductDetail(size, color, pictureUrl, price, quantitySold, quantityStock, description, productId) " //
                + " values (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, productDetail.getSize());
        pstm.setString(2, productDetail.getColor());
        pstm.setString(3, productDetail.getPictureUrl());
        pstm.setBigDecimal(4, productDetail.getPrice());
        pstm.setInt(5, productDetail.getQuantitySold());
        pstm.setInt(6, productDetail.getQuantityStock());
        pstm.setString(7, productDetail.getDescription());
        pstm.setLong(8, productDetail.getProductId());

        int rowsInserted = pstm.executeUpdate();

        return rowsInserted > 0;
    }

    public static List<ProductDetail> searchProductDetail(String searchInput, Long productId) throws SQLException, ClassNotFoundException {

        String sql;
        if (productId == 999) {
            sql = "Select prod.*, pro.name as proName, pro.pictureUrl as proPictureUrl, pro.quantitySold as proQuantitySold, pro.quantityStock as proQuantityStock, pro.description as proDescription, pro.categoryId as proCategoryId, pro.isDelete as proIsDelete from dboProductDetail as prod" //
                    + " join dboProduct as pro on prod.productId = pro.id"//
                    + " where pro.name LIKE ? and pro.isDelete = 0";
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + searchInput + "%");
            ResultSet rs = pstm.executeQuery();
            List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
            while (rs.next()) {
                ProductDetail productDetail = new ProductDetail(rs.getLong("id"), rs.getString("size"), rs.getString("color"), rs.getString("pictureUrl"), rs.getBigDecimal("price"),
                        rs.getInt("quantitySold"), rs.getInt("quantityStock"), rs.getString("description"), rs.getLong("productId"));
                Product product = new Product(rs.getLong("productId"), rs.getString("proName"), rs.getString("proPictureUrl"), rs.getInt("proQuantitySold"),
                        rs.getInt("proQuantityStock"), rs.getString("proDescription"), rs.getLong("proCategoryId"), rs.getBoolean("proIsDelete"));
                productDetail.setProduct(product);
                productDetails.add(productDetail);
            }
            return productDetails;
        } else {
            sql = "Select prod.*, pro.name as proName, pro.pictureUrl as proPictureUrl, pro.quantitySold as proQuantitySold, pro.quantityStock as proQuantityStock, pro.description as proDescription, pro.categoryId as proCategoryId, pro.isDelete as proIsDelete from dboProductDetail as prod" //
                    + " join dboProduct as pro on prod.productId = pro.id"//
                    + " where pro.name LIKE ? and prod.ProductId = ? and pro.isDelete = 0";
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + searchInput + "%");
            pstm.setLong(2, productId);
            ResultSet rs = pstm.executeQuery();
            List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
            while (rs.next()) {
                ProductDetail productDetail = new ProductDetail(rs.getLong("id"), rs.getString("size"), rs.getString("color"), rs.getString("pictureUrl"), rs.getBigDecimal("price"),
                        rs.getInt("quantitySold"), rs.getInt("quantityStock"), rs.getString("description"), rs.getLong("productId"));
                Product product = new Product(rs.getLong("productId"), rs.getString("proName"), rs.getString("proPictureUrl"), rs.getInt("proQuantitySold"),
                        rs.getInt("proQuantityStock"), rs.getString("proDescription"), rs.getLong("proCategoryId"), rs.getBoolean("proIsDelete"));
                productDetail.setProduct(product);
                productDetails.add(productDetail);
            }
            return productDetails;
        }
    }

    public static boolean updateProductDetail(ProductDetail productDetail) throws SQLException, ClassNotFoundException {

        if (productDetail.getPictureUrl().equals("NULL")) {
            String sql = "update dboProductDetail" //
                    + " set size = ?, color = ?, price = ?, quantitySold = ?, quantityStock = ?, description = ?, productId = ?" //
                    + " where id = ?";
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, productDetail.getSize());
            pstm.setString(2, productDetail.getColor());
            pstm.setBigDecimal(3, productDetail.getPrice());
            pstm.setInt(4, productDetail.getQuantitySold());
            pstm.setInt(5, productDetail.getQuantityStock());
            pstm.setString(6, productDetail.getDescription());
            pstm.setLong(7, productDetail.getProductId());
            pstm.setLong(8, productDetail.getId());

            int rowsInserted = pstm.executeUpdate();

            return rowsInserted > 0;
        } else {
            String sql = "update dboProductDetail" //
                    + " set size = ?, color = ?, pictureUrl = ?, price = ?, quantitySold = ?, quantityStock = ?, description = ?, productId = ?" //
                    + " where id = ?";
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, productDetail.getSize());
            pstm.setString(2, productDetail.getColor());
            pstm.setString(3, productDetail.getPictureUrl());
            pstm.setBigDecimal(4, productDetail.getPrice());
            pstm.setInt(5, productDetail.getQuantitySold());
            pstm.setInt(6, productDetail.getQuantityStock());
            pstm.setString(7, productDetail.getDescription());
            pstm.setLong(8, productDetail.getProductId());
            pstm.setLong(9, productDetail.getId());

            int rowsInserted = pstm.executeUpdate();

            return rowsInserted > 0;
        }
    }

    public static boolean deleteProductDetail(long id) throws SQLException, ClassNotFoundException {

        String sql = "delete dboProductDetail" //
                + " where Id = ?";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setLong(1, id);
        int rowsUpdated = pstm.executeUpdate();
        return rowsUpdated > 0;
    }
}
