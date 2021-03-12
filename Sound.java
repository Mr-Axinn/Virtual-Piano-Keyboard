import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
public class Sound extends JFrame
{
    Clip clip;
    Clip clop;
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
            if(written)
            {   
      
                timing = (n.soundingTime * (60000/tempo)) + startTime;

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
        
            Long start = System.currentTimeMillis();
            
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
            System.out.println(n.name);
            System.out.println("There was an error playing a note");
        }
    }
  
    public void playSoundNormal(String name)
    {
        try{
            URL url = this.getClass().getClassLoader().getResource(name);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            
            
            Long start = System.currentTimeMillis();
            
             if(name.contains("P") || name.contains("Ref"))
             {
                 clip.start();
                
            }
            else
            {
                 FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                 double gain = 0.55;   
                 float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
                 volume.setValue(dB);
                 
            
                 clip.start();
                  
            }
        }
        catch(Exception e)
        {
           System.out.println("There was an error with the piece"); 
           clip.stop();
        }
    }
    
}