/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaguiforfirebase;

import com.google.firebase.database.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phureekanusont
 */
public class DataServices {
    private static LinkedList<HistoryData> temperatureData = new LinkedList<HistoryData>();
    private static LinkedList<HistoryData> smokeData = new LinkedList<HistoryData>();
    private static boolean finish = false;
    
    public static void turnOff(){
        finish = false;
    }
    
    public static boolean isFinish(){
        return finish;
    }
    
    public static void waiting(){
        while(!isFinish()){
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void setTemperatureData(LinkedList<HistoryData> temperatureData) {
        DataServices.temperatureData = temperatureData;
    }

    public static void setSmokeData(LinkedList<HistoryData> smokeData) {
        DataServices.smokeData = smokeData;
    }
    
    public static LinkedList<HistoryData> getTemperatureData(){
        return temperatureData;
    }
    
    public static LinkedList<HistoryData> getSmokeData(){
        return smokeData;
    }
    
    public static void initialTempData(){
    SecurityUtil.getRef("Sensor/tempHistory").addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
           finish = false;
           LinkedList<HistoryData> temporary = new LinkedList<HistoryData>();
           int counter = 0;
           float allTemp = 0;
           for (DataSnapshot date: dataSnapshot.getChildren()){
               for (DataSnapshot time: date.getChildren()){
                   for (DataSnapshot sec: time.getChildren()){
                       allTemp += Float.parseFloat(sec.getValue(String.class));
                       counter+=1;
                   }
               }
               if (counter != 0){
                    temporary.add(new HistoryData(date.getKey(), allTemp/counter));
               }
               counter = 0;
               allTemp = 0;
           }
           temperatureData = temporary;
           finish = true;
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
        });
    }
    
    public static void initialSmokeData(){
    SecurityUtil.getRef("Sensor/smokeHistory").addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
           finish = false;
           int counter = 0;
           float allSmoke = 0;
           LinkedList<HistoryData> temporary = new LinkedList<HistoryData>();
           for (DataSnapshot date: dataSnapshot.getChildren()){
               for (DataSnapshot time: date.getChildren()){
                   for (DataSnapshot sec: time.getChildren()){
                       allSmoke += Float.parseFloat(sec.getValue(String.class));
                       counter+=1;
                   }
               }
               if (counter != 0){
                    temporary.add(new HistoryData(date.getKey(), allSmoke/counter));
               }
               counter = 0;
               allSmoke = 0;
           }
           smokeData = temporary;
           finish = true;
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
        });
     
    }
    
}
