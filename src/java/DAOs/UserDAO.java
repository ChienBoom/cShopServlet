/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Connection.ConnectionUtil;
import Models.Account;
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
    
}
