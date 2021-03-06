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
    public Clip getClip()
    {
        return clip;
        //clip.stop();
        
    }
    public void playSoundNormal(String name)
    {
        try{
            URL url = this.getClass().getClassLoader().getResource(name);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            //ais = AudioSystem.getAudioInputStream(new File(fname));
            //clip = AudioSystem.getClip();
            //gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            
            Long start = System.currentTimeMillis();
            //System.out.println(name);
             if(name.contains("P") || name.contains("Ref"))
             {
                 //FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                 //volume.setValue(6.02f); 
                 clip.start();
                 //this.stopClip();
                 //while(System.currentTimeMillis() - start < 10000)
                 //{   
            
          //       }
                // clip.stop();
                 //System.out.println("here");
                 
                 //System.out.println("Here" + clip);
                 //clip.stop();
            }
            else
            {
                 FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                 double gain = 0.65;   
                 float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
                 volume.setValue(dB);
                 
                 //volume.setValue(0f);
                 clip.start();
                  
            }
        }
        catch(Exception e)
        {
           System.out.println("here2" + e); 
           clip.stop();
        }
    }
    public void stopClip()
    {
        //System.out.println(clip);
        clip.stop();
    }
}