package humanresources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    Connection conn;

    public Connection getConn() throws InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/hr?"
                    + "user=root&password=HHolaquetal**15");
            System.out.println("Connected");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Connection failed");
        }

        return conn;
    }
}
