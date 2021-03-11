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
                        
                         if(easyFormatting && individualNote.contains("/"))
                         {
                           
                             String[] chordNotes = individualNote.split("/", 100);
                             String length = chordNotes[0].substring(0,1);
                             leng = Integer.parseInt(length);
                             if(i > 0)
                             {
                                 times = notes.get(i-1).soundingTime + notes.get(i-1).length;
                             }
                             else
                             {
                                 times = 0;
                             }
                             for(int j = 0; j < chordNotes.length; j++)
                             {
                                 individualNote = chordNotes[j];
                                
                                 notes.add(new Note(leng, individualNote.substring(1, 2).toUpperCase() + individualNote.substring(2, 3), times));
                             }
                         }
                         else {
                             
                             String length = individualNote.substring(0,1);
                             String timing = individualNote.substring(3, individualNote.length());
                             String note = individualNote.substring(1, 2).toUpperCase() + individualNote.substring(2, 3);
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
                             if(!correctFormat)
                             {
                                 System.out.println("You have added your notes inccorectly");
                                 System.out.println("Make sure they are in the format LengthNameTime (1c1)");
                             }
                             else if(leng > 4 || leng < 1)
                             {
                                 System.out.println(leng);
                                 System.out.println("Make sure your length is between 1 and 4");
                             }
                             else if(note.length() > 2 || note.length() < 1)
                             {
                                 System.out.println("Make sure you have specified a valid note");
                             }
                             else if(!easyFormatting && (times < 0 || times > 100))
                             {
                                 System.out.println("Make sure you have entered a valid timing");
                             }
                             else
                             {
                                 notes.add(new Note(leng, note, times));
                                 
                             }
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
