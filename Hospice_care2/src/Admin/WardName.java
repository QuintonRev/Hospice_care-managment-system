/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

/**
 *
 * @author Reverside
 */
public enum WardName {
    Nelson_Mandela, Chris_Hani, Winnie_Mandela;
    
    private WardName (){
    }
    
    public String value(){
        return name();
       
    }
    
    public static WardName fromvalue(String v){
        return valueOf(v);
    }
}
