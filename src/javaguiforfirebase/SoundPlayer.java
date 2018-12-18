/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaguiforfirebase;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.*;

/**
 *
 * @author phureekanusont
 */
public class SoundPlayer {
    
    private static String fileLocation = "alarm.wav";
    private static FileInputStream inputStream = null;
    private static AudioStream audioStream;
    private static AudioData md;
    private static ContinuousAudioDataStream con;
    
    public static void setFileLocation(String fileLocation){
        SoundPlayer.fileLocation = fileLocation;
    }
    
    public static void setSound(String path){
        setFileLocation(path);
        Main.getGui().getLabel(14).setText(path);
        try {
            inputStream = new FileInputStream(SoundPlayer.fileLocation);
            audioStream = new AudioStream(SoundPlayer.inputStream);
            md = audioStream.getData();
            con = new ContinuousAudioDataStream(md);
            SecurityUtil.setValue("Controller/sounds", path);
        } catch (FileNotFoundException ex) {
            Main.getGui().getLabel(14).setText("File not found!");
            SecurityUtil.setValue("Controller/sounds", "alarm.wav");
        } catch (IOException ex) {
            Main.getGui().getLabel(14).setText("Error");
            SecurityUtil.setValue("Controller/sounds", "alarm.wav");
        }
    }
    
    public static void play(){
        AudioPlayer.player.start(con);
    }
    
    public static void stop(){
        AudioPlayer.player.stop(con);
    }
    
}
