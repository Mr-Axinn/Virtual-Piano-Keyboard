import java.util.ArrayList;
import java.io.*;
public class SaveMusic
{
    ArrayList<Note> notes;
    BufferedWriter bw;
    boolean written;
    public SaveMusic(ArrayList<Note> Notes, boolean Written)
    {
       notes = Notes;
       written = Written;
    }
    public void saveMusic(String fileName)
    {
        int soundingTime;
        int writtenLength;
        int error = 0;
        boolean first = true;
        try{ 
            FileWriter writing = new FileWriter(fileName);
            bw = new BufferedWriter(writing);
            for(int i = 0; i < notes.size(); i++)
            {
                //System.out.println(notes);
                Note note = notes.get(i);
                if(!written)
                {
                     //System.out.println("here");
                     writtenLength = note.length / 100;
                     soundingTime = ((note.soundingTime * 120)/60000);
                     //System.out.println("notes's time " + note.soundingTime);
                    // System.out.println("actual time " + soundingTime);
                }
                else
                {
                    writtenLength = note.length;
                    soundingTime = note.soundingTime;
                }
                if(writtenLength < 1)
                {
                    writtenLength = 1;
                }
                /*
                if(!written)
                {
                    int remainder = soundingTime % 2;
                    if(remainder != 0)
                    {
                        if(remainder > 1)
                        {
                            soundingTime += (2- remainder);
                        }
                        else
                        {
                            soundingTime -= remainder; 
                        }
                      
                    }
                }
                */
                if(first && !written)
                {
                    first = false;
                    //error = soundingTime;
                }
                bw.write(writtenLength + note.name + (soundingTime-error) + " ");
            }
        }
        catch(Exception e) {
            System.out.println("here" + e);
        }
        try{
            bw.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
