/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Connection.ConnectionUtil;
import Models.Order;
import Models.OrderDetail;
import Models.User;
import java.sql.Statement;
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
public class OrderDAO {

    public static List<Order> getAllOrder() throws SQLException, ClassNotFoundException {

        String sql = "Select ord.*, us.fullName as usFullName, us.email as usEmail from dboOrder as ord" //
                + " join dboUser as us on ord.userId = us.id"//
                + " join dboAccount as ac on us.AccountId = ac.id"//
                + " where ac.isDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
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

    public static List<Order> getOrderByUserId(long userId) throws SQLException, ClassNotFoundException {

        String sql = "Select ord.*, us.fullName as usFullName, us.email as usEmail from dboOrder as ord" //
                + " join dboUser as us on ord.userId = us.id"//
                + " join dboAccount as ac on us.AccountId = ac.id"//
                + " where ac.isDelete = 0 and ord.userId = ?";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setLong(1, userId);
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

    public static boolean addOrder(Order order) throws SQLException, ClassNotFoundException {

        String sql = "insert into dboOrder(orderAt, totalPrice, description, userId, isDelete, status) " //
                + " values (?, ?, ?, ?, ?, ?)";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setTimestamp(1, Timestamp.valueOf(order.getOrderAt()));
        pstm.setBigDecimal(2, order.getTotalPrice());
        pstm.setString(3, order.getDescription());
        pstm.setLong(4, order.getUserId());
        pstm.setBoolean(5, order.getIsDelete());
        pstm.setString(6, order.getStatus());

        int rowsInserted = pstm.executeUpdate();

        return rowsInserted > 0;
    }

    public static boolean orderProduct(Order order, OrderDetail orderDetail) throws SQLException, ClassNotFoundException {

        String sql = "insert into dboOrder(orderAt, totalPrice, description, userId, isDelete, status) " //
                + " values (?, ?, ?, ?, ?, ?)";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstm.setTimestamp(1, Timestamp.valueOf(order.getOrderAt()));
        pstm.setBigDecimal(2, order.getTotalPrice());
        pstm.setString(3, order.getDescription());
        pstm.setLong(4, order.getUserId());
        pstm.setBoolean(5, order.getIsDelete());
        pstm.setString(6, order.getStatus());

        int rowsInserted = pstm.executeUpdate();

        if (rowsInserted > 0) {
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderDetail.setOrderId(generatedKeys.getLong(1));
                    boolean insertOrderDetail = OrderDetailDAO.addOrderDetail(orderDetail);
                    if (insertOrderDetail) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
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
