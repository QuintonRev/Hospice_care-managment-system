
package LoginApp;

/**
 *
 * @author Reverside
 */
public enum option {
    Admin, Nurse;
    
    private option (){}
    
    public String value(){
        return name();
       
    }
    
    public static option fromvalue(String v){
        return valueOf(v);
    }
    
}
