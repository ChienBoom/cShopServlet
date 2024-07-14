/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author MinhChien
 */
public class ConnectionUtil {

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        return SqlConnectionUtil.getSQLServerConnection_SQLJDBC();
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
