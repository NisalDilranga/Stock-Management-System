
package Controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbControler {
    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/applestore-02", "root", "");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
