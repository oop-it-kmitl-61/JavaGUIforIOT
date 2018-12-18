/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaguiforfirebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.*;
import com.google.firebase.database.*;
import java.io.*;
import java.util.logging.*;
/**
 *
 * @author phureekanusont
 */
public class SecurityUtil {
    
    public static String DATABASE_URL = "https://java-iot-bed31.firebaseio.com/";
    
    public static void setUp(){
        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream("service-account.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(DATABASE_URL)
                .build();
            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SecurityUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SecurityUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static DatabaseReference getRef(String path){
        return FirebaseDatabase.getInstance().getReference(path);
    }
    
    public static void getValue(String path, SecurityData storage){
        SecurityUtil.getRef(path).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
           String val = dataSnapshot.getValue(String.class);
           boolean output = path.contains("Sensor/Data");
           if (output) storage.setCurrent(val);
           else {if (val == null || "".equals(val)){
                switch(path){
                    case "Controller/max_smoke": val = "400"; break;
                    case "Controller/max_temp": val = "35"; break;
                    }
                SecurityUtil.setValue(path, val);
                }
                storage.setControl(val);
           }          
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
        });
    }

    
    public static void setValue(String path, String val){
        DatabaseReference myRef = SecurityUtil.getRef(path);
        myRef.setValue(val, null);
    }
    
    public static void setValue(String path, float val){
        DatabaseReference myRef = SecurityUtil.getRef(path);
        myRef.setValue(val, null);
    }

    
}
