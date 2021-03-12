import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
public class PlayMusic
{
    ArrayList<Note> notes;
    ArrayList<thread> threads;
    int[] timings;
    boolean written;
    //Length: 1, 2, 3, 4
    //Note: A, B, C
    //Time: 100
    public PlayMusic(ArrayList<Note> Notes, boolean Written)
    {
       notes = Notes;
       written = Written;
       threads  = new ArrayList<thread>();
    }
    public void play(int tempo, int reverb) 
    {
        try{
        final long startTime = System.currentTimeMillis();
        for(int i = 0; i < notes.size(); i++)
        {
            Sound s = new Sound(notes.get(i),startTime, written, tempo, reverb);
            threads.add(new thread(s));
        }
       
        for(int i = 0; i < threads.size(); i++)
        {
            threads.get(i).start();
        }
        
    }
    catch(Exception e)
    {
        System.out.println("There has been a problem playing this piece");
    }
    }
    
}

