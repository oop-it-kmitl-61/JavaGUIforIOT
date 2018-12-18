/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaguiforfirebase;

import com.google.firebase.database.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author phureekanusont
 */
public class Main {
    static SecurityGUI gui = new SecurityGUI();
    static SecurityData Temperature = new SecurityData();
    static SecurityData Smoke = new SecurityData();
    private static String service, DB_URL;
    
    public static SecurityGUI getGui(){
        return gui;
    }
    
    public static void addListener(String path, SecurityData storage, JTextField output){
        SecurityUtil.getRef(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              String post = dataSnapshot.getValue(String.class);
              output.setText(post);
              blink(path, output);
              if (path.contains("Sensor/Data")){
                storage.setCurrent(post);
              } else { storage.setControl(post);}
              if (path.contains("smoke") || path.contains("Smoke")){
                warnSmoke();
              }
              else {
                warnTemp();
              }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
              System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
    
    public static void blink(String path, JTextField output){
        try {
                output.setEnabled(true);
                output.setForeground(new java.awt.Color(255, 51, 51));
                TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (path.contains("Sensor")){
                output.setEnabled(false);
            }
            output.setForeground(new java.awt.Color(0, 0, 0));
        }
    }
    
    public static void warnSmoke(){
        
        if (Smoke.is_danger()){ 
            gui.getLabel(7).setForeground(new java.awt.Color(255, 51, 51));
            gui.getLabel(7).setText("มีปริมาณควันมากกว่าปกติ!");
            SoundPlayer.loop();
        }
        else {
            gui.getLabel(7).setForeground(new java.awt.Color(51, 255, 51));
            gui.getLabel(7).setText("ควันสถานะปกติ");
            SoundPlayer.stop();
        }
    }
    public static void warnTemp(){
    if (Temperature.is_danger()){
            gui.getLabel(9).setForeground(new java.awt.Color(255, 51, 51));
            gui.getLabel(9).setText("อุณหภูมิสูงเกินค่าที่ตั้งไว้!");
            SoundPlayer.loop();
        }
        else {
            gui.getLabel(9).setForeground(new java.awt.Color(51, 255, 51));
            gui.getLabel(9).setText("อุณหภูมิสถานะปกติ");
            SoundPlayer.stop();
        }
    }
    
    static void initialValue(JTextField tf1, JTextField tf2, SecurityData storage){
        tf1.setText(storage.getCurrent());
        tf2.setText(storage.getControl());
        tf2.setEnabled(true);
    }
    
    static void setValueToStorage(String control, String type, SecurityData storage){
        SecurityUtil.getValue("Controller/" + control, storage);
        SecurityUtil.getValue("Sensor/Data/" + type, storage);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SecurityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SecurityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SecurityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SecurityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        JSONParser parser = new JSONParser();
        Object obj = null;
        JSONObject config = null;
        try {
            obj = parser.parse(new FileReader("config.json"));
            config = (JSONObject) obj;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        service = (String) config.get("service_account_path");
        DB_URL = (String) config.get("firebase_url");
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });

        SecurityUtil.setUp(DB_URL, service);

        setValueToStorage("max_temp", "Temperature", Temperature);
        setValueToStorage("max_smoke", "Smoke", Smoke);
        
        while(Temperature.getControl().equals("")){
            gui.getTextField(2).setText("loading");
        }
        
        initialValue(gui.getTextField(1), gui.getTextField(2), Temperature);
        initialValue(gui.getTextField(3), gui.getTextField(4), Smoke);

        addListener("Sensor/Data/Temperature", Temperature, gui.getTextField(1));
        addListener("Controller/max_temp", Temperature, gui.getTextField(2));
        addListener("Sensor/Data/Smoke", Smoke, gui.getTextField(3));
        addListener("Controller/max_smoke", Smoke, gui.getTextField(4));
        
 
        SecurityUtil.getRef("Controller/sounds").addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
           String Sound = dataSnapshot.getValue(String.class);
           
           if (Sound == null){
               SoundPlayer.setSound("alarm.wav");
           }
           else {
               SoundPlayer.setSound(Sound);
           }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
        });
        
    }
}
