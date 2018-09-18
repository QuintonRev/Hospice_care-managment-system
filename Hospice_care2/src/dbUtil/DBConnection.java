
package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {
    
    // Constant Variables used to connect to my database
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONN = "jdbc:mysql://localhost/hospice";
    
    public static void main(String[] args) throws SQLException {
        
           Connection con = null;
           Statement stmt = null;
           ResultSet rs = null;
           
           try{
           con = dbConnection1.getConnection();
           stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs = stmt.executeQuery("Select * FROM patient");
           
           rs.last();
           
           System.out.println("connected");
           System.out.println(rs.getRow());
           
        }catch (SQLException e){
            System.err.print(e);
        }
           finally{
               if(rs != null){
                   rs.close();
               }
               if(stmt != null){
                   stmt.close();
               }
               if(con != null){
                   con.close();
               }
           }
    }
}
    
