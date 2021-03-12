import java.io.*;
import java.util.ArrayList;
public class ReadMusic
{
    ArrayList<Note> notes = new ArrayList<Note>();
    int leng;
    int times;
    boolean correctFormat;
    BufferedReader br;
    public ReadMusic(String fileName, boolean easyFormatting, boolean recorded)  {
        try {
           FileReader reading = new FileReader(fileName);
           br = new BufferedReader(reading);
       
           String currentLine = null;
           currentLine = br.readLine();
           int time = 0; 
           correctFormat = true;

           if(currentLine != null) {
                 String[] stringNotes = currentLine.split(" ",1000);
                 for(int i = 0; i < stringNotes.length; i++) {
                     
                     String individualNote = stringNotes[i];
                     if(individualNote.length() > 3)
                     {
                                String length = individualNote.substring(0,1);
                                String timing;
                                String note;
                             if(individualNote.contains("-"))
                             {
                                 //System.out.println("here");   
                                 timing = individualNote.substring(4, individualNote.length());
                                    note = individualNote.substring(1, 2).toUpperCase() + individualNote.substring(2, 4);
                             } 
                             else
                             {
                                    
                                    timing = individualNote.substring(3, individualNote.length());
                                    note = individualNote.substring(1, 2).toUpperCase() + individualNote.substring(2, 3);
                             }
                             leng = 0;
                             times = 0;
                             try {
                                 leng = Integer.parseInt(length);
                                 if(!easyFormatting)
                                 {
                                     times = Integer.parseInt(timing);
                                 }
                                 else if(i > 0)
                                 {
                                     times = notes.get(i-1).soundingTime + notes.get(i-1).length;
                                 }
                                 else
                                 {
                                     times = 0;
                                 }
                             }
                             catch(Exception e)
                             {
                                 correctFormat = false;
                             }
                             if(correctFormat)
                             {
                                 notes.add(new Note(leng, note, times));
                                 
                             }
                        
                    }
                }
            }
            }
        
        catch(Exception e)
        {
                System.out.println("Something went wrong. Please try again.");
        }
    }
}
