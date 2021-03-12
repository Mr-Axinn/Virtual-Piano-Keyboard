import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

 public class thread extends Thread 
 {
        Sound s;   

        public thread(Sound S)
        {
            s = S;
           
        }
        public void run() 
        {
            if(s.n.name.contains("P") || s.n.name.contains("Ref"))
            {
                    s.playSoundNormal(s.n.name);
            }
            else
            {
                s.playSound();
            }
        }
      
 }