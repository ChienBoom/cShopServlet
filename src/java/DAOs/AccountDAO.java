/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Connection.ConnectionUtil;
import Models.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MinhChien
 */
public class AccountDAO {

    public AccountDAO() {
    }

    public static Account findAccount(String userName, String password) throws SQLException, ClassNotFoundException {

        String sql = "Select * from dboAccount a " //
                + " where a.username = ? and a.password= ?";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            Account account = new Account(rs.getLong("Id"), rs.getString("username"), rs.getString("password"),
                    rs.getString("role"), rs.getBoolean("isActive"), rs.getBoolean("isDelete"));
            return account;
        }
        return null;
    }
    
    public static boolean findAccountByUsername(String userName) throws SQLException, ClassNotFoundException {

        String sql = "Select * from dboAccount a " //
                + " where a.username = ? and a.isDelete= 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return true;
        }
        return false;
    }

    public static boolean registerAccount(String userName, String password) throws SQLException, ClassNotFoundException {

        String sql = "insert into dboAccount(username, password, role, IsActive, IsDelete) " //
                + " values (?, ?, ?, 1, 0)";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        pstm.setString(3, "MEMBER");

        int rowsInserted = pstm.executeUpdate();

        return rowsInserted > 0;
    }

    public static boolean banAccount(long id) throws SQLException, ClassNotFoundException {

        String sql = "update dboAccount" //
                + " set IsActive = 0" //
                + " where Id = ? and IsDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setLong(1, id);
        int rowsUpdated = pstm.executeUpdate();
        return rowsUpdated > 0;
    }
    
    public static boolean unBanAccount(long id) throws SQLException, ClassNotFoundException {

        String sql = "update dboAccount" //
                + " set IsActive = 1" //
                + " where Id = ? and IsDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setLong(1, id);
        int rowsUpdated = pstm.executeUpdate();
        return rowsUpdated > 0;
    }
    
    public static boolean deleteAccount(long id) throws SQLException, ClassNotFoundException {

        String sql = "update dboAccount" //
                + " set IsDelete = 1" //
                + " where Id = ? and IsDelete = 0";

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setLong(1, id);
        int rowsUpdated = pstm.executeUpdate();
        return rowsUpdated > 0;
    }
    
}
