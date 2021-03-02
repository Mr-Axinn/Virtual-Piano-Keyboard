import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
public class Sound extends JFrame
{
    Clip clip;
    Note n;
     long startTime;
     boolean written;
     int tempo;
     int reverb;
    public Sound(Note N, long StartTime, boolean Written, int Tempo, int Reverb) {
         n = N;
         startTime = StartTime;
         written = Written;
         tempo = Tempo;
         reverb = Reverb;
    }
    public void playSound()  {
        long timing;
        int length;
        try {
            //* 300
            //System.out.println(written);
            if(written)
            {   
                //System.out.println("here");
                timing = (n.soundingTime * (60000/tempo)) + startTime;
                //System.out.println(timing);
                length = n.length * (reverb * 600);
            }
            else
            {
                timing = n.soundingTime + startTime;
                if(n.length < 100)
                {
                    n.length = 100;
                }
                length = n.length * 7;
            }
            
            
            while(System.currentTimeMillis() < timing)
            {
                
                
            }
            //* 500
            //7 (without pedal)
            //10 (with pedal)
           
            Long start = System.currentTimeMillis();
            //System.out.println(n.name);
            URL url = this.getClass().getClassLoader().getResource(n.name + ".wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
             
            while(System.currentTimeMillis() - start < length)
            {   
            
            }
            clip.stop();
            
        }
        catch(Exception e) {
            System.out.println("here" + e);
        }
    }
    public void playSoundNormal(String name)
    {
        try{
            URL url = this.getClass().getClassLoader().getResource(name);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            Long start = System.currentTimeMillis();
            if(name.equals("PianoBeats"))
            {
                while(System.currentTimeMillis() - start < 2000)
                {   
            
                }
            }
            
            
        }
        catch(Exception e)
        {
           System.out.println("here2" + e); 
        }
    }
}