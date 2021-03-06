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
            System.out.println("non main thread" + this);
        }
        public void run() 
        {
            //System.out.println(s.n.name + System.currentTimeMillis());
            if(s.n.name.contains("P") || s.n.name.contains("Ref"))
            {
                    //clip.start();
                    
                    //System.out.println("here1" + clop);
                    s.playSoundNormal(s.n.name);
                    //s.stopClip();
                    //clop = clip;
                    //System.out.println("begin" + clop);
            }
            else
            {
                s.playSound();
            }
        }
        public void end()
        {
            //System.out.println("here" + s);
            //s.stopClip();
            
            
            //s.stopClip(clop);
        }
 }