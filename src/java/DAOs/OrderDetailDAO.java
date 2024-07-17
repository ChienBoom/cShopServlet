/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Connection.ConnectionUtil;
import Models.Order;
import Models.OrderDetail;
import Models.Product;
import Models.ProductDetail;
import Models.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MinhChien
 */
public class OrderDetailDAO {
    public static List<OrderDetail> getAllOrderDetail() throws SQLException, ClassNotFoundException {

        String sql = "Select ordd.*, od.orderAt as odOrderAt, prod.size as prodSize, prod.color as prodColor, prod.price as prodPrice, pro.name as proName from dboOrderDetail as ordd" //
                + " join dboOrder as od on ordd.OrderId = od.id"//
                + " join dboProductDetail as prod on ordd.productDetailId = prod.id"//
                + " join dboProduct as pro on prod.productId = pro.id"//
                + " where order.isDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        while (rs.next()) {
            OrderDetail orderDetail = new OrderDetail(rs.getLong("id"), rs.getLong("orderId"), rs.getLong("productDetailId"), rs.getInt("quantity"), 
                    rs.getBigDecimal("totalPrice"), rs.getString("description"));
            Order order = new Order();
            order.setOrderAt(rs.getTimestamp("odOrderAt").toLocalDateTime());
            ProductDetail productDetail = new ProductDetail();
            productDetail.setSize(rs.getString("prodSize"));
            productDetail.setColor(rs.getString("prodColor"));
            productDetail.setPrice(rs.getBigDecimal("prodPrice"));
            Product product = new Product();
            product.setName(rs.getString("proName"));
            productDetail.setProduct(product);
            orderDetail.setOrder(order);
            orderDetail.setProductDetail(productDetail);
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    public static List<OrderDetail> getOrderDetailsByOrderId(long orderId) throws SQLException, ClassNotFoundException {

        String sql = "Select ordd.*, od.orderAt as odOrderAt, prod.size as prodSize, prod.color as prodColor, prod.price as prodPrice, pro.name as proName from dboOrderDetail as ordd" //
                + " join dboOrder as od on ordd.OrderId = od.id"//
                + " join dboProductDetail as prod on ordd.productDetailId = prod.id"//
                + " join dboProduct as pro on prod.productId = pro.id"//
                + " where od.isDelete = 0 and ordd.OrderId = ?";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setLong(1, orderId);
        ResultSet rs = pstm.executeQuery();
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        while (rs.next()) {
            OrderDetail orderDetail = new OrderDetail(rs.getLong("id"), rs.getLong("orderId"), rs.getLong("productDetailId"), rs.getInt("quantity"), 
                    rs.getBigDecimal("totalPrice"), rs.getString("description"));
            Order order = new Order();
            order.setOrderAt(rs.getTimestamp("odOrderAt").toLocalDateTime());
            ProductDetail productDetail = new ProductDetail();
            productDetail.setSize(rs.getString("prodSize"));
            productDetail.setColor(rs.getString("prodColor"));
            productDetail.setPrice(rs.getBigDecimal("prodPrice"));
            Product product = new Product();
            product.setName(rs.getString("proName"));
            productDetail.setProduct(product);
            orderDetail.setOrder(order);
            orderDetail.setProductDetail(productDetail);
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    public static boolean addOrderDetail(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {

        String sql = "insert into dboOrderDetail(orderId, productDetailId, quantity, totalPrice, description) " //
                + " values (?, ?, ?, ?, ?)";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setLong(1, orderDetail.getOrderId());
        pstm.setLong(2, orderDetail.getProductDetailId());
        pstm.setInt(3, orderDetail.getQuantity());
        pstm.setBigDecimal(4, orderDetail.getTotalPrice());
        pstm.setString(5, orderDetail.getDescription());

        int rowsInserted = pstm.executeUpdate();

        return rowsInserted > 0;
    }

    public static List<Order> searchOrder(Date startDate, Date endDate) throws SQLException, ClassNotFoundException {

        String sql = "Select ord.*, us.fullName as usFullName, us.email as usEmail from dboOrder as ord" //
                + " join dboUser as us on ord.userId = us.id"//
                + " join dboAccount as ac on us.AccountId = ac.id"//
                + " where ac.isDelete = 0 and ord.orderAt >= ? and ord.orderAt <= ?";
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setTimestamp(1, new Timestamp(startDate.getTime()));
        pstm.setTimestamp(2, new Timestamp(endDate.getTime()));
        ResultSet rs = pstm.executeQuery();
        List<Order> orders = new ArrayList<Order>();
        while (rs.next()) {
            Order order = new Order(rs.getLong("id"), rs.getTimestamp("orderAt").toLocalDateTime(), rs.getBigDecimal("totalPrice"), rs.getString("description"), 
                    rs.getLong("userId"), rs.getBoolean("isDelete"), rs.getString("status"));
            User user = new User();
            user.setFullName(rs.getString("usFullName"));
            user.setEmail(rs.getString("usEmail"));
            order.setUser(user);
            orders.add(order);
        }
        return orders;
    }

}
