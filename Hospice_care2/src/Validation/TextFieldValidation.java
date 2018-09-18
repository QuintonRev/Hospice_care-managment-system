/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.regex.Pattern;
import javafx.scene.control.TextArea;

/**
 *
 * @author Reverside
 */
public class TextFieldValidation {
    
    
    public static boolean isTextFieldNot2018(TextField date){
        
        boolean b = false;
        String strDate = date.getText();
        String str = new String(strDate);
        String SubStr1 = new String("/");
        int index = str.lastIndexOf(SubStr1) + 1;
        
        int year = Integer.parseInt(strDate.substring(index));
        
        if(year <2018  && !date.getText().isEmpty())
            b = true;
        
        return b;
    }
    
     public static boolean isTextFieldNot2018(TextField date, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!isTextFieldNot2018(date)){
            
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return b;
    }
    
    public static boolean isPasswordMatched(PasswordField tf1, PasswordField tf2){
        boolean b = false;
        if(tf1.getText().equals(tf2.getText()))
            b = true;
        
        return b;
        
    }
    
    public static boolean isPasswordMatched(PasswordField tf1, PasswordField tf2, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!isPasswordMatched(tf1,tf2)){
            b = false;
            msg = errorMessage;
            
        }
        lb.setText(msg);
        return b;
    }
    
    public static boolean isValidEmail(TextField tf){
        boolean b = false;
        String pattern = "^(.+)@(.+)$";
        if(tf.getText().matches(pattern))
            b = true;
        return b;
    }
    
    public static boolean isValidEmail(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        
        if(!isValidEmail(tf)){
            
            b = false;
            msg = errorMessage;
            
        }
        lb.setText(msg);
        return b;
    }
    
    public static boolean isTextFieldNotEmpty(TextField tf){
        boolean b = false;
        if(tf.getText().length() !=0 || !tf.getText().isEmpty())
            b = true;
        return b;
    }
    
     public static boolean isTextFieldNotEmpty(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!isTextFieldNotEmpty(tf)){
            
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return b;
    }
     public static boolean istextFieldTypeNumber(TextField tf){
         boolean b=false;
         if(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+"))
         b=true;
         return b;
         
     }
          public static boolean istextFieldTypeNumber(TextField tf, Label lb,String errorMessage){
              
         boolean b=true;
         String msg = null;
         if(!istextFieldTypeNumber(tf)){
         b=true;
         msg = errorMessage;
         }
         lb.setText(msg);
         return b;
     }
          
       public static boolean istextFieldId13(TextField tf){
         boolean b=false;
         if(tf.getText().length()==13)
         b=true;
         return b;
         
     }
          public static boolean istextFieldId13(TextField tf, Label lb,String errorMessage){
              
         boolean b=true;
         String msg = null;
         if(!istextFieldId13(tf)){
         b=true;
         msg = errorMessage;
         }
         lb.setText(msg);
         return b;
     }
    
            public static boolean isTextFieldNotEmptyAddress(TextArea tf){
        boolean b = false;
        if(tf.getText().length() !=0 || !tf.getText().isEmpty())
            b = true;
        return b;
    }
    
     public static boolean isTextFieldNotEmptyAddress(TextArea tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!isTextFieldNotEmptyAddress(tf)){
            
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return b;
     }
       public static boolean istextFieldZip4(TextField tf){
         boolean b=false;
         if(tf.getText().length()== 4)
         b=true;
         return b;
         
     }
          public static boolean istextFieldZip4(TextField tf, Label lb,String errorMessage){
              
         boolean b=true;
         String msg = null;
         if(!istextFieldZip4(tf)){
         b=true;
         msg = errorMessage;
         }
         lb.setText(msg);
         return b;
     }
}
