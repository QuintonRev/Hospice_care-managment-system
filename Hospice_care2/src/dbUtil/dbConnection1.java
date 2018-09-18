
package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Reverside
 */
public class dbConnection1 {
      // Constant Variables used to connect to my database
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONN = "jdbc:mysql://localhost/hospice";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(CONN, USERNAME, PASSWORD);
    }
    
}
