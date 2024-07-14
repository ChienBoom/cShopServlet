/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MinhChien
 */
public class SqlConnectionUtil {
    /// Kết nối vào SQL Server.
    // (Sử dụng thư viện SQLJDBC)
    public static Connection getSQLServerConnection_SQLJDBC() //
            throws ClassNotFoundException, SQLException {

        // Chú ý: Thay đổi các thông số kết nối cho phù hợp.
        String hostName = "localhost";
        String sqlInstanceName = "WIN-VUINPNQ1IOK\\SQLEXPRESS";
        String database = "cshop";
        String userName = "sa";
        String password = "1214";

        return getSQLServerConnection_SQLJDBC(hostName, sqlInstanceName, database, userName, password);
    }

    // Kết nối tới SQLServer, sử dụng thư viện SQLJDBC.
    private static Connection getSQLServerConnection_SQLJDBC(String hostName, //
            String sqlInstanceName, String database, String userName, String password)//
            throws ClassNotFoundException, SQLException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Cấu trúc URL Connection dành cho SQLServer
        // Ví dụ:
        // jdbc:sqlserver://ServerIp:1433/SQLEXPRESS;databaseName=simplehr
        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" //
                + ";instance=" + sqlInstanceName + ";databaseName=" + database + ";encrypt=true;trustServerCertificate=true;";

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
