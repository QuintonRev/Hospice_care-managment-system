/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginApp;

import dbUtil.dbConnection1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Reverside
 */
public class LoginModel {
    Connection connection;
    public String username = null;
    public String UserData;
    
    public LoginModel(){
        try{
            this.connection = dbConnection1.getConnection();
     }catch (SQLException ex){
            ex.printStackTrace();
        }
        if (this.connection == null){
            System.exit( 1);
    }
     
    }
    public boolean isDatabaseConnected(){
        return this.connection != null;
}
    public Object getUser(){
        
        return username;
    }
    
     public boolean isLogin(String user, String pass, String opt) throws Exception {

        
        
        String sql = "SELECT * FROM login where email = ? and password=? and division=?";
        PreparedStatement pr = null;
        ResultSet rs = null;
       
                
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);
            pr.setString(3, opt);


            rs = pr.executeQuery();
            boolean bolll;

            if (rs.next()) {
                username = rs.getString(1);
                
                return true;
            }
            return false;
        }
        catch (SQLException ex)

        {
            return false;
        }
        //
        finally {
            {
        //call the close method/API
        pr.close();
        rs.close();
    }

        }
     }
}


