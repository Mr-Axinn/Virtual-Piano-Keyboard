import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.border.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import javax.swing.JComponent;
import java.awt.Component;
import java.util.HashMap;
import java.awt.Graphics2D;
import java.lang.Object.*;
public class MusicWriter implements ActionListener, ItemListener
{
    JFrame pictureFrame;
    JPanel panel;
    JLabel instructions;
    JLabel instructions1;
    JLabel instructions2;
    JLabel instructions3;
    JLabel instructions4;
    JLabel instructions5;
    JLabel instructions6;
    JLabel instructions7;
    
    
    JLabel tempo;
    JLabel tempo1;
    DefaultListModel listModel;
    JList list;
    JComboBox jbox;
    JComboBox jbox1;
    
    boolean firstTime = true;
    ArrayList<Note> notes1 = new ArrayList<Note>();
    
    JLabel message;
    
    JTextField fieldTempo;
    JLabel reverb;
    JLabel reverb1;
    
    JButton save;
    JButton playBack;
    JButton loadSaved;
    JButton playBackSong;
    
    JButton remove;
    JButton clearAll;
    
    JTextField field;
    JButton button;
    HashMap<String, String> map;
    JLabel song;
    JLabel song1;
    JLabel song2;
    
    int reverbInt;
    int tempoInt;
    
    
    String songString = "";
    String songString1 = "";
    String songString2 = "";
    String entireSong = "";
    String[] notes;
    public MusicWriter()
    {
        this.noteArray();
        panel = new JPanel();
        panel.setLayout(null);
        pictureFrame = new JFrame("Music Writer"); // create the JFrame
        pictureFrame.setSize(900, 600);
        pictureFrame.setResizable(false);  // allow the user to resize it
        pictureFrame.getContentPane().setLayout(new BorderLayout()); // use border layout
        pictureFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        field = new JTextField();
        field.setBounds(50, 275, 90, 40);
        panel.add(field);
        
        button = new JButton("Submit");
        button.setBounds(160, 275, 90, 40);
        button.addActionListener(this);
        panel.add(button);
        
        reverbInt = 1;
        tempoInt = 60;
        
        remove = new JButton("Remove");
        remove.setBounds(520, 275, 90, 40);
        remove.addActionListener(this);
        panel.add(remove);
        
        clearAll= new JButton("Clear All");
        clearAll.setBounds(640, 275, 90, 40);
        clearAll.addActionListener(this);
        panel.add(clearAll);
        
        save = new JButton("Save");
        save.setBounds(600, 40, 90, 40);
        save.addActionListener(this);
        panel.add(save);
        
        loadSaved = new JButton("Load Prev.");
        loadSaved.setBounds(700, 40, 90, 40);
        loadSaved.addActionListener(this);
        panel.add(loadSaved);
        
        playBack = new JButton("Play Note");
        playBack.setBounds(280, 275, 90,40);
        playBack.addActionListener(this);
        panel.add(playBack);
        
        playBackSong = new JButton("Play Song");
        playBackSong.setBounds(400, 275, 90, 40);
        playBackSong.addActionListener(this);
        panel.add(playBackSong);
        
        
        
        message = new JLabel();
        message.setFont(new Font("Courier",Font.PLAIN, 20));
        message.setBounds(50, 500, 700, 30);
        panel.add(message);
        
        song = new JLabel();
        song.setFont(new Font("Courier",Font.PLAIN, 20));
        song.setBounds(50, 350, 10, 30);
        panel.add(song);
        
        song1 = new JLabel();
        song1.setFont(new Font("Courier",Font.PLAIN, 20));
        song1.setBounds(50, 400, 10, 30);
        panel.add(song1);
        
        song2 = new JLabel();
        song2.setFont(new Font("Courier",Font.PLAIN, 20));
        song2.setBounds(50, 450, 10, 30);
        panel.add(song2);
        
        
        instructions =  new JLabel();
        instructions.setText("Please enter your song into the text field");
        instructions.setFont(new Font("Courier",Font.PLAIN, 20));
        instructions.setBounds(47, 80, (20*instructions.getText().length()+ 200), 40);
        
        
        instructions1 =  new JLabel();
        instructions1.setText("A note should be written in the format length/note/timing (slashes are not included)");
        instructions1.setFont(new Font("Courier",Font.PLAIN, 15));
        instructions1.setBounds(50, 120, (15*instructions.getText().length() + 200), 40);
        
        
        instructions2 =  new JLabel();
        instructions2.setText("Length (the amount of time a note is played) should be written as a value from 1 to 4");
        instructions2.setFont(new Font("Courier",Font.PLAIN, 15));
        instructions2.setBounds(50, 135, (15*instructions.getText().length()+ 200), 40);
        
        instructions3 =  new JLabel();
        instructions3.setText("Notes should be written in the range from c4 to b5 with c4 being middle c");
        instructions3.setFont(new Font("Courier",Font.PLAIN, 15));
        instructions3.setBounds(50, 150, (15*instructions.getText().length()+ 200), 40);
        
        instructions4 =  new JLabel();
        instructions4.setText("Timing (when a note is played) should be an integer");
        instructions4.setFont(new Font("Courier",Font.PLAIN, 15));
        instructions4.setBounds(50, 165, (15*instructions.getText().length()), 40);
       
        instructions5 =  new JLabel();
        instructions5.setText("Example: 3c410 where length is 3, the note is c4, and the timing is 10");
        instructions5.setFont(new Font("Courier",Font.PLAIN, 15));
        instructions5.setBounds(50, 195, (15*instructions.getText().length()+ 200), 40);
        
        instructions6 =  new JLabel();
        instructions6 =  new JLabel("Individual notes of chords should be written seperately but with the same timings");
        instructions6.setFont(new Font("Courier",Font.PLAIN, 15));
        instructions6.setBounds(50, 180, (15*instructions.getText().length()+ 200), 40);
        
        instructions7 =  new JLabel();
        instructions7 =  new JLabel("Example: 3c410 and 3c510 will be played as a chord");
        instructions7.setFont(new Font("Courier",Font.PLAIN, 15));
        instructions7.setBounds(50, 210, (15*instructions.getText().length()+ 200), 40);
        
        
        tempo = new JLabel();
        tempo.setFont(new Font("Courier",Font.PLAIN, 20));
        tempo.setBounds(640, 345, (15*instructions.getText().length()), 30);
        tempo.setText(" Tempo: ");
        panel.add(tempo);
        
        tempo1 = new JLabel();
        tempo1.setFont(new Font("Courier",Font.BOLD, 20));
        tempo1.setBounds(735, 345, (15*instructions.getText().length()), 30);
        tempo1.setText("60 BPM");
        panel.add(tempo1);
        
        reverb = new JLabel();
        reverb.setFont(new Font("Courier",Font.PLAIN, 20));
        reverb.setBounds(640, 395, (15*instructions.getText().length()), 30);
        reverb.setText("Reverb: ");
        panel.add(reverb);
        
        reverb1 = new JLabel();
        reverb1.setFont(new Font("Courier",Font.BOLD, 20));
        reverb1.setBounds(735, 395, (15*instructions.getText().length()), 30);
        reverb1.setText("1");
        panel.add(reverb1);
        
        jbox1 = new JComboBox();
        jbox1.setBounds(820, 395, 80, 30);
        jbox1.addItem("1");
        jbox1.addItem("2");
        jbox1.addItem("3");
        jbox1.addItem("4");
        jbox1.addItemListener(this);
        panel.add(jbox1);
        
        //fieldTempo = new JTextField();
        //fieldTempo.setBounds(750, 225, 50, 40);
        //panel.add(fieldTempo);
        
        
        jbox = new JComboBox();
        jbox.setBounds(820, 350, 80, 30);
        jbox.addItem("40");
        jbox.addItem("60");
        jbox.addItem("80");
        jbox.addItem("100");
        jbox.addItem("120");
        jbox.addItem("140");
        jbox.addItem("160");
        jbox.addItemListener(this);
        panel.add(jbox);
        
        
        
       
        
        panel.add(instructions);
        panel.add(instructions1);
        panel.add(instructions2);
        panel.add(instructions3);
        panel.add(instructions4);
        panel.add(instructions5);
        panel.add(instructions6);
        panel.add(instructions7);
        
        panel.setVisible(true);
        pictureFrame.add(panel);
        pictureFrame.show(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton) e.getSource();
        String text = field.getText();
        Note note;
        if(j == button)
        {
            try{
                
                note =  this.correctFormatting(text);
            }
            catch (Exception r)
            {
                message.setText("Something went wrong entering your note");
                note = null;
            }
             if(!(note == null))
             {
                 try{
                 field.setText("");
                 entireSong += (note.format() + " ");
                 if(notes1.size() < 8) 
                 {
                     songString += (note.format() + " ");
                     song.setBounds(50, 350, ((songString.length() + 1) * 20), 30);
                     song.setText(songString);
                     notes1.add(note);
                 }
                 else if(notes1.size() < 17)
                 {
                     songString1 += (note.format() + " ");
                     song1.setBounds(50, 400, ((songString1.length() + 1) * 20), 30);
                     song1.setText(songString1);
                     notes1.add(note);
                 }
                 else if(notes1.size() < 24)
                 {
               
                     songString2 += (note.format() + " ");
                     song2.setBounds(50, 450, ((songString1.length() + 1) * 20), 30);
                     song2.setText(songString2);
                     notes1.add(note);
                 }
                 else
                 {
                     message.setText("There is no more space in your song");
                 }
                }
                catch(Exception k)
                {
                    message.setText("Something went wrong in adding notes");
                }
            }
            
        }
        else if (j == playBack)
        {
            field.setText("");
            try{
                note =  this.correctFormatting(text);
                if(note != null)
                {
                    Sound s = new Sound(note, 1, true, tempoInt, reverbInt);
                    s.playSound();
                }
                else
                {
                    message.setText("The note you have entered can't be played");
                }
            }
            catch(Exception g)
            {
                message.setText("The note you have entered can't be played");
            }
        }
        else if (j == playBackSong)
        {
            field.setText("");
            try{    
                if(!(notes1.size() == 0))
                {
                    PlayMusic pm = new PlayMusic(notes1, true);
                    pm.play(tempoInt, reverbInt);
                }
                else
                {
                    message.setText("There is no song to play currently");
                }
            }
            catch(Exception h)
            {
                message.setText("There is no song to play currently");
            }
        }
        else if (j == remove)
        {
            field.setText("");
            try{
            if(notes1.size() > 0)
            {
                
                if(notes1.size() == 1)
                {
                    notes1.remove(notes1.size() - 1);
                    songString = "";
                    song.setText("");
                }
                else
                {
                    notes1.remove(notes1.size() - 1);
                    songString = "";
                    for(int i = 0; i < notes1.size(); i++)
                    {
                        if(i < 8)
                        {
                            songString += notes1.get(i).format() + " ";
                        }
                        else if(i < 16)
                        {
                            songString1 += notes1.get(i).format() + " ";
                        }
                        else
                        {
                            songString2 += notes1.get(i).format() + " ";
                        }
                    }
                     if(songString.length() > 0)
                    {
                        song.setBounds(50, 350, ((songString.length() + 1) * 20), 30);
                        song.setText(songString);
                    }
                    if(songString1.length() > 0)
                    {
                        song.setBounds(50, 400, ((songString.length() + 1) * 20), 30);
                        song.setText(songString1);
                    }
                    if(songString2.length() > 0)
                    {
                        song.setBounds(50, 450, ((songString.length() + 1) * 20), 30);
                        song.setText(songString2);
                    }
                }
            }
            else
            {
                message.setText("There is nothing to remove");
            }
        }
        catch(Exception l)
        {
            message.setText("Something went wrong while removing notes");
        }
        }
        else if (j == clearAll)
        {
            field.setText("");
            songString = "";
            songString1 = "";
            songString2 = "";
            song.setText("");
            song1.setText("");
            song2.setText("");
            message.setText("Cleared");
            notes1 = new ArrayList<Note>();
        }
        else if (j == save)
        {
            if(notes1.size() > 1)
            {
                SaveMusic sm = new SaveMusic(notes1, true);
                sm.saveMusic("musicWritten.txt");
            }
            else
            {
                message.setText("There must be at least one note to save");
            }
        }
        else if(j == loadSaved)
        {
            if(notes1.size() > 0)
            {
                 message.setText("Clear existing notes in order to load previous songs");
            }
            else
            {
                try {
                songString = "";
                songString1 = "";
                songString2 = "";
                ReadMusic rm = new ReadMusic("musicWritten.txt", false, false);
                notes1 = rm.notes;
                for(int i = 0; i < notes1.size(); i++)
                {
                    if(i < 8)
                    {
                        songString += notes1.get(i).format() + " ";
                    }
                    else if(i < 16)
                    {
                        songString1 += notes1.get(i).format() + " ";
                    }
                    else
                    {
                        songString2 += notes1.get(i).format() + " ";
                    }
                }
                if(songString.length() > 0)
                {
                    song.setBounds(50, 350, ((songString.length() + 1) * 20), 30);
                  
                    song.setText(songString);
                }
                if(songString1.length() > 0)
                {
                    song1.setBounds(50, 400, ((songString1.length() + 1) * 20), 30);
                    song1.setText(songString1);
                }
                if(songString2.length() > 0)
                {
                    song2.setBounds(50, 450, ((songString2.length() + 1) * 20), 30);
                    song2.setText(songString2);
                }
                if(notes1.size() == 0)
                {
                    message.setText("You have no previously saved songs");
                }
            }
             catch(Exception p)
            {
                message.setText("Something went wrong loading saved notes");
            }
        }   
        }
       
    }
    public void itemStateChanged(ItemEvent e) 
    { 
        // if the state combobox is changed 
        if (e.getSource() == jbox) { 
            tempo1.setText(jbox.getSelectedItem() + " BPM");
            tempoInt = Integer.parseInt(jbox.getSelectedItem() + "");
        } 
        else if(e.getSource() == jbox1)
        {
            reverb1.setText(jbox1.getSelectedItem() + "");
            reverbInt = Integer.parseInt(jbox1.getSelectedItem() + "");
        }
    } 
    public void noteArray()
    {
        notes = new String[14];
        notes[0] = "c4";
        notes[1] = "d4";
        notes[2] = "e4";
        notes[3] = "f4";
        notes[4] = "g4";
        notes[5] = "a4";
        notes[6] = "b4";
        notes[7] = "c5";
        notes[8] = "d5";
        notes[9] = "e5";
        notes[10] = "f5";
        notes[11] = "g5";
        notes[12] = "a5";
        notes[13] = "b5";
    }
    public Note correctFormatting(String text) throws Exception
    {
           
               
            String note = text;
            int times = 0;
            String note1 = "";
            int leng = 0;
            boolean correctFormat = true;
            if(!(note.length() < 4 || note.length() > 6))
            {
                String length = note.substring(0,1);
                String timing = note.substring(3, note.length());
                note1 = note.substring(1, 2).toUpperCase() + note.substring(2, 3);
                
                try {
                     leng = Integer.parseInt(length);
                     times = Integer.parseInt(timing);
                     
                     if(leng > 4 || leng < 1)
                     {
                         message.setText("The length should be between 1 and 4");
                         return null;
                         
                        
                     }
                     if(times < 0 || times > 100)
                     {
                        message.setText("Your music timing should be between 1 and 100");
                        return null;
                     }
                     boolean exists = false;
                     for(int i = 0; i < notes.length; i++)
                     {
                         if(note1.equalsIgnoreCase(notes[i]))
                         {
                             exists = true;
                             i = 100;
                         }
                     }
                     if(!exists)
                     {
                         message.setText("The note you entered does not exist");
                         return null;
                     }
                     
                }
                catch(Exception d)
                {
                    message.setText("You have entered a note incorrectly");
                   
                    field.setText("Bad Input");
                    return null;
                }
            }
            else
            {
                field.setText("Bad Input");
                return null;
            }
            return new Note(leng, note1, times);
        }
 }
 
