 public class thread extends Thread
 {
        Sound s;   
        public thread(Sound S)
        {
            s = S;
        }
        public void run()
        {
            //System.out.println(s.n.name + System.currentTimeMillis());
            if(s.n.name.contains("Beat"))
            {
                s.playSoundNormal(s.n.name);
            }
            else
            {
                s.playSound();
            }
        }
 }