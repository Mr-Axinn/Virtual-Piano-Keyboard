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
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
public class TitleScreen implements ActionListener
{
    JFrame pictureFrame;
    JPanel panel;
    JLabel explanation;
    
    JButton button1;
    JButton button2;
    
    JButton izzyMusic;
    JButton musicRecorded;
    JButton musicWritten;
    JLabel error;
    Audio audio;
    JLabel instructions;
    JLabel instructions1;
    Image img;
    Image imgCopy;
    JLabel instructions2;
    JLabel instructions3;
    public TitleScreen()
    {
       
        
    }
    public void createScreen() 
    {
        Picture pic = new Picture();
        img = pic.getImage();
         try {
            imgCopy = ImageIO.read(new File("ActualPiano copy2.jpg"));
        } catch (IOException e) {
            System.out.println("There has been an error reading in an image");
        }
        panel = new JPanel();
        panel.setLayout(null);
        pictureFrame = new JFrame("Title Screen"); // create the JFrame
        pictureFrame.setSize(750, 600);
        pictureFrame.setResizable(false);  // allow the user to resize it
        pictureFrame.getContentPane().setLayout(new BorderLayout()); // use border layout
        pictureFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // when close stop
       
        
        button1 = new JButton("Keyboard");
        button1.setBounds(50, 170, 120, 40);
        button1.addActionListener(this);
        
        
        button2 = new JButton("Music Writer");
        button2.setBounds(180, 170, 120, 40);
        button2.addActionListener(this);
        
        izzyMusic = new JButton("Izzy's Music");
        izzyMusic.setBounds(570, 170, 120, 40);
        izzyMusic.addActionListener(this);
        panel.add(izzyMusic);
        
        musicRecorded = new JButton("Recorded Music");
        musicRecorded.setBounds(440, 170, 120, 40);
        musicRecorded.addActionListener(this);
        panel.add(musicRecorded);
        
        musicWritten = new JButton("Written Music");
        musicWritten.setBounds(310, 170, 120, 40);
        musicWritten.addActionListener(this);
        panel.add(musicWritten);
        
        
        error = new JLabel();
        error.setBounds(50, 400, 500, 30);
        error.setFont(new Font("Courier",Font.PLAIN, 20));
        panel.add(error);
        
        instructions = new JLabel();
        instructions.setText("In this program you will be able to write, play, and record music"); 
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        //System.out.println(Component.CENTER_ALIGNMENT);
        instructions.setFont(new Font("Courier",Font.PLAIN, 15));
        instructions.setBounds(75, 80, (15*instructions.getText().length()), 40);
       
        
        
        instructions1 = new JLabel();
        instructions1.setText("using a virtual piano. Click 'keyboard' for the virtual keyboard"); 
        //instructions1.setAlignmentX(Component.CENTER_ALIGNMENT);
        //System.out.println(Component.CENTER_ALIGNMENT);
        instructions1.setFont(new Font("Courier",Font.PLAIN, 15));
        instructions1.setBounds(78, 95, (15*instructions.getText().length()), 40);
        
        instructions2 = new JLabel();
        instructions2.setText("or 'music writer' to begin writing your own music manually."); 
        //instructions1.setAlignmentX(Component.CENTER_ALIGNMENT);
        //System.out.println(Component.CENTER_ALIGNMENT);
        instructions2.setFont(new Font("Courier",Font.PLAIN, 15));
        instructions2.setBounds(85, 110, (15*instructions.getText().length()), 40);
        
        
        
        explanation = new JLabel();
        explanation.setText("Welcome to Izzy's Virtual Piano");
        explanation.setAlignmentX(Component.CENTER_ALIGNMENT);
        explanation.setBounds(85, 30, (30*explanation.getText().length()), 30);
        explanation.setFont(new Font("Courier",Font.BOLD, 30));
        
  
        
        panel.add(explanation);
        panel.add(instructions);
        panel.add(instructions1);
        panel.add(instructions2);
        panel.add(button1);
        panel.add(button2);
        
        JLabel imageIcon = new JLabel(new ImageIcon(imgCopy));
        imageIcon.setBounds(18,200,700,252);
        panel.add(imageIcon);
        
        panel.setVisible(true);
        pictureFrame.add(panel);
        pictureFrame.show(true);
        
        
        
    } 
    public void actionPerformed(ActionEvent e) 
    {
        JButton j = (JButton) e.getSource();
        if(j == button1)
        {
            
            CreateKeyboard ck = new CreateKeyboard();
            ck.createAndInitPictureFrame(img);
            error.setText("");
        }
        else if (j == button2)
        {
             MusicWriter ms = new MusicWriter();
             error.setText("");
        }
        else if (j == izzyMusic)
        {
            try{
                //URL url = this.getClass().getClassLoader().getResource("IzzyPiano.wav");
                //AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                //Clip clip = AudioSystem.getClip();
                //clip.open(audioIn);
                //clip.start();
                if (audio == null) {
                    audio = new MainTheme();
                }

                if (audio.isPlaying()) {
   
                    audio.reset();
                } 
                else {
                    audio.play();
                    error.setText("Press 'Izzy's Music' again to stop");
                }
            }
            catch(Exception d)
            {
                error.setText("There has been an error playing Izzy's Piece");
            }
        }
        else if (j == musicRecorded)
        {
            ReadMusic rm = new ReadMusic("musicRecorded.txt", false, false);
            if(rm.notes.size() > 0)
            {
                error.setText("");
                PlayMusic pm = new PlayMusic(rm.notes, true);
                pm.play(120,2);
            }
            else
            {
                error.setText("There is no recorded music");
            }
        }
        else if (j == musicWritten)
        {
            ReadMusic rm = new ReadMusic("musicWritten.txt", false, false);
            if(rm.notes.size() > 0)
            {
                error.setText("");
                PlayMusic pm = new PlayMusic(rm.notes, true);
                pm.play(60,2);
            }
            else
            {
                error.setText("There is no written music");
            }
        }
    }
    
    
}
