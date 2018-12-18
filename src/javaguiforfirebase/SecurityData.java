/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaguiforfirebase;

/**
 *
 * @author phureekanusont
 */
public class SecurityData {
    private String current = "";
    private String control = "";

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }
    
    public boolean is_danger(){
        if (Double.parseDouble(current) > Double.parseDouble(control)){
            return true;
        }
        else return false;
    }
    
}
