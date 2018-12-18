/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaguiforfirebase;

import java.io.*;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

/**
 *
 * @author phureekanusont
 */
public class SoundPlayer {
    
    private static String fileLocation = "alarm.wav";
    private static Clip clip;
    
    public static void setFileLocation(String fileLocation){
        SoundPlayer.fileLocation = fileLocation;
    }
    
    public static void setSound(String path){
        setFileLocation(path);
        Main.getGui().getLabel(14).setText(path);
        try {
            File file = new File(path);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
             // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else {
                Main.getGui().getLabel(14).setText("Sound: file not found: " + path);
                SecurityUtil.setValue("Controller/sounds", "alarm.wav");
            }
        }
        catch (Exception e) {
            Main.getGui().getLabel(14).setText("Error");
            SecurityUtil.setValue("Controller/sounds", "alarm.wav");
        }
        
    }
    
    public static void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    public static void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void stop(){
        clip.stop();
    }
}
