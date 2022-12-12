package humanresources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    Connection conn;

    public Connection getConn() throws InstantiationException, IllegalAccessException {
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:sqlite:C:/temp/hr.db");
            System.out.println("Connected");
        } catch (SQLException ex) {
            System.out.println("Connection failed");
        }

        return conn;
    }
}
