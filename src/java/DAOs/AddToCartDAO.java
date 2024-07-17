/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Connection.ConnectionUtil;
import Models.AddToCart;
import Models.Category;
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
public class AddToCartDAO {
    public static List<AddToCart> getAllAddToCart() throws SQLException, ClassNotFoundException {

        String sql = "Select atc.*, prod.id asprodId, prod.size as prodSize, prod.color as prodColor, prod.price as prodPrice, pro.name as proName, pro.pictureUrl as proPictureUrl from dboAddToCart as atc"
                + " join dboProductDetail as prod on atc.productDetailId = prod.Id"
                + " join dboProduct as pro on prod.productId = pro.Id"
                + " where pro.isDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<AddToCart> addToCarts = new ArrayList<AddToCart>();
        while (rs.next()) {
            AddToCart addToCart = new AddToCart(rs.getLong("id"), rs.getString("username"), rs.getLong("productDetailId"), rs.getInt("quantity"));
            ProductDetail productDetail = new ProductDetail();
             productDetail.setId(rs.getLong("prodId"));
            productDetail.setColor(rs.getString("prodColor"));
            productDetail.setSize(rs.getString("prodSize"));
            productDetail.setPrice(rs.getBigDecimal("prodPrice"));
            Product product = new Product();
            product.setName(rs.getString("proName"));
            product.setPictureUrl(rs.getString("proPictureUrl"));
            productDetail.setProduct(product);
            addToCart.setProductDetail(productDetail);
            addToCarts.add(addToCart);
        }
        return addToCarts;
    }
    
    public static List<AddToCart> getAddToCartByUsername(String username) throws SQLException, ClassNotFoundException {

        String sql = "Select atc.*, prod.id as prodId, prod.size as prodSize, prod.color as prodColor, prod.price as prodPrice, pro.name as proName, pro.pictureUrl as proPictureUrl from dboAddToCart as atc"
                + " join dboProductDetail as prod on atc.productDetailId = prod.Id"
                + " join dboProduct as pro on prod.productId = pro.Id"
                + " where pro.isDelete = 0 and atc.username = ?";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        ResultSet rs = pstm.executeQuery();
        List<AddToCart> addToCarts = new ArrayList<AddToCart>();
        while (rs.next()) {
            AddToCart addToCart = new AddToCart(rs.getLong("id"), rs.getString("username"), rs.getLong("productDetailId"), rs.getInt("quantity"));
            ProductDetail productDetail = new ProductDetail();
            productDetail.setId(rs.getLong("prodId"));
            productDetail.setColor(rs.getString("prodColor"));
            productDetail.setSize(rs.getString("prodSize"));
            productDetail.setPrice(rs.getBigDecimal("prodPrice"));
            Product product = new Product();
            product.setName(rs.getString("proName"));
            product.setPictureUrl(rs.getString("proPictureUrl"));
            productDetail.setProduct(product);
            addToCart.setProductDetail(productDetail);
            addToCarts.add(addToCart);
        }
        return addToCarts;
    }
    
    public static boolean insertAddToCart(AddToCart addToCart) throws SQLException, ClassNotFoundException {

        String sql = "insert into dboAddToCart(username, productDetailId, quantity) " //
                + " values (?, ?, ?)";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, addToCart.getUsername());
        pstm.setLong(2, addToCart.getProductDetailId());
        pstm.setInt(3, addToCart.getQuantity());

        int rowsInserted = pstm.executeUpdate();

        return rowsInserted > 0;
    }
    
    public static boolean updateAddToCart(AddToCart addToCart) throws SQLException, ClassNotFoundException {

        String sql = "update dboAddToCart" //
                + " set quantity = ?"
                + " where id = ?";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, addToCart.getQuantity());
        pstm.setLong(2, addToCart.getId());

        int rowsInserted = pstm.executeUpdate();

        return rowsInserted > 0;
    }
    
    public static boolean deleteAddToCart(long id) throws SQLException, ClassNotFoundException {

        String sql = "delete dboAddToCart" //
                + " where Id = ?";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setLong(1, id);
        int rowsUpdated = pstm.executeUpdate();
        return rowsUpdated > 0;
    }
    
}
