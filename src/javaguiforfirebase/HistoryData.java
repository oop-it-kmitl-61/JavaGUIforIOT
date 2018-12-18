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
public class HistoryData {
    private String date;
    private float average;
    
    public HistoryData(String date, String average){
        this.date = date;
        this.average = Float.parseFloat(average);
    }
    
    public HistoryData(String date, float average){
        this.date = date;
        this.average = average;
    }
    
    public String getDetail(){
        return date + " : " + average;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
    
}
