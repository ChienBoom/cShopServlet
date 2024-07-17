/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Connection.ConnectionUtil;
import Models.Account;
import Models.Product;
import Models.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MinhChien
 */
public class UserDAO {

    public static List<User> findUserMember() throws SQLException, ClassNotFoundException {

        String sql = "Select * from dboUser us join dboAccount ac on us.AccountId = ac.Id" //
                + " where ac.role = ? and ac.isDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, "MEMBER");
        ResultSet rs = pstm.executeQuery();
        List<User> users = new ArrayList<User>();
        while (rs.next()) {
            User user = new User(rs.getLong("Id"), rs.getString("fullName"), rs.getString("email"),
                    rs.getString("sex"), rs.getString("address"), rs.getString("pictureUrl"), rs.getDate("dOB"), rs.getLong("accountId"));
            user.setUsername(rs.getString("username"));
            user.setRole(rs.getString("role"));
            user.setIsActive(rs.getBoolean("isActive"));
            user.setIsDelete(rs.getBoolean("isDelete"));
            users.add(user);
        }
        return users;
    }

    public static User getUserByUsername(String username) throws SQLException, ClassNotFoundException {

        String sql = "Select * from dboUser us join dboAccount ac on us.AccountId = ac.Id" //
                + " where ac.username = ? and ac.isDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            User user = new User(rs.getLong("Id"), rs.getString("fullName"), rs.getString("email"),
                    rs.getString("sex"), rs.getString("address"), rs.getString("pictureUrl"), rs.getDate("dOB"), rs.getLong("accountId"));
            user.setUsername(rs.getString("username"));
            user.setRole(rs.getString("role"));
            user.setIsActive(rs.getBoolean("isActive"));
            user.setIsDelete(rs.getBoolean("isDelete"));
            return user;
        }
        return null;
    }

    public static boolean updateProfile(User user) throws SQLException, ClassNotFoundException {

        if (user.getPictureUrl().equals("NULL")) {
            String sql = "update us" //
                    + " set us.fullName = ?, us.email = ?, us.sex = ?, us.address = ?, us.DOB = ?" //
                    + " from dboUser as us"
                    + " join dboAccount as ac on us.AccountId = ac.Id"
                    + " where ac.username = ?";
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getFullName());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getSex());
            pstm.setString(4, user.getAddress());
            pstm.setDate(5, new java.sql.Date(user.getDOB().getTime()));
            pstm.setString(6, user.getUsername());

            int rowsInserted = pstm.executeUpdate();

            return rowsInserted > 0;
        } else {
            String sql = "update us" //
                    + " set us.fullName = ?, us.email = ?, us.sex = ?, us.address = ?, us.DOB = ?, us.pictureUrl = ?" //
                    + " from dboUser as us"
                    + " join dboAccount as ac on us.AccountId = ac.Id"
                    + " where ac.username = ?";
            System.out.println("username: " + user.getUsername());
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getFullName());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getSex());
            pstm.setString(4, user.getAddress());
            pstm.setDate(5, new java.sql.Date(user.getDOB().getTime()));
            pstm.setString(6, user.getPictureUrl());
            pstm.setString(7, user.getUsername());

            int rowsInserted = pstm.executeUpdate();

            return rowsInserted > 0;
        }
    }

    public static boolean changeAvatar(User user) throws SQLException, ClassNotFoundException {

        String sql = "update us" //
                + " set us.pictureUrl = ?" //
                + " from dboUser as us"
                + " join dboAccount as ac on us.AccountId = ac.Id"
                + " where ac.username = ?";
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getPictureUrl());
        pstm.setString(2, user.getUsername());

        int rowsInserted = pstm.executeUpdate();

        return rowsInserted > 0;
    }

}
