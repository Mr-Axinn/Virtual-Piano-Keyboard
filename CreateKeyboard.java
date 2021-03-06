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
//import javafx.scene.input.KeyEvent;


public class CreateKeyboard implements ItemListener, ActionListener, KeyListener
{
    JFrame pictureFrame;
    //JPanel panel;
    JButton whiteKeyBase0;
    boolean firt = true;
    
    JButton recorder;
    JButton playBack;
    JButton save;
    
    JComboBox beats;
    
    HashMap<String, Integer> map1;

    ArrayList<Note> notes;
    ArrayList<JButton> buttons;
    ArrayList<String> names;
    HashMap<String,String> map;
    HashMap<String, Long> heldButtons;
    HashMap<String, Integer> heldButtons1;
    Long beginTime;
    String beat;
    Clip clip;
    boolean record = false;
    Graphic panel;
    playKey play; 
    Image img;
    Picture pic;
    JLabel imageIcon;
    public void createAndInitPictureFrame(Image Img)
    {
        initializeButtons();
        initializeLists();
         
        setButtonBounds();
        
        img = Img;
       // pic = Pic;
        notes = new ArrayList<Note>();
        
        //panel = new JPanel();
        panel = new Graphic(img);
        pictureFrame = new JFrame("VirtualPiano"); // create the JFrame
        pictureFrame.setSize(1400, 400);
        pictureFrame.setResizable(false);  // allow the user to resize it
        pictureFrame.getContentPane().setLayout(new BorderLayout()); // use border layout
        pictureFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // when close stop
        LayoutManager overlay = new OverlayLayout(panel);
        panel.setLayout(null);

        //https://freesound.org/people/TEDAgame/packs/25405/?page=6#sound
        
   
        heldButtons = new HashMap<String, Long>();
        heldButtons1 = new HashMap<String, Integer>();
        
        
        beats = new JComboBox();
        beats.setBounds(35, 26, 100, 30);
        beats.addItem("None");
        beats.addItem("PianoBeat");
        beats.addItem("Monotone");
        beats.addItem("Vinyl");
        beats.addItemListener(this);
        beats.setFocusable(false);
        beats.requestFocus(false);
        panel.add(beats);
        
        
        recorder = new JButton();
        recorder.setText("Start");
        recorder.setBounds(1160,26,70,40);
        recorder.addActionListener(this);
        recorder.setFocusable(false);
        recorder.requestFocus(false);
        panel.add(recorder);
        
        playBack = new JButton();
        //playBack.setText("Play back");
        playBack.setBounds(1240, 26, 70, 40);
        playBack.setOpaque(false);
        playBack.setContentAreaFilled(false);
        playBack.setBorderPainted(false);
        playBack.addActionListener(this);
        playBack.setFocusable(false);
        playBack.requestFocus(false);
        panel.add(playBack);
     
        
        save = new JButton();
        //save.setText("Save");
        save.setBounds(1320, 26, 70, 40);
        save.addActionListener(this);
        save.setFocusable(false);
        save.requestFocus(false);
        save.setOpaque(false);
        save.setContentAreaFilled(false);
        save.setBorderPainted(false);
        panel.add(save);
        
      
            //this.setUpButton(buttons.get(i));
            whiteKeyBase0.addKeyListener(this);
            this.makeInvisible(whiteKeyBase0);
            panel.add(whiteKeyBase0);
            whiteKeyBase0.setActionCommand(map.get(names.get(0).toLowerCase()));
        
            beginTime = System.currentTimeMillis();
        
        play = new playKey(); 
        for(int i = 0; i < names.size(); i++)
        {
            whiteKeyBase0.getInputMap().put(KeyStroke.getKeyStroke(names.get(i)), "c.wav");
            whiteKeyBase0.getActionMap().put("c.wav", play);
        }
        
       // imageIcon = new JLabel(new ImageIcon(img));
        //imageIcon.setBounds(100,10,1108,252);
        //imageIcon.setOpaque(false);
        panel.startTime(100,200);
        //panel.add(imageIcon,-1);
        
        panel.setOpaque(false);
        panel.setVisible(true);
        pictureFrame.add(panel);
        pictureFrame.show(true);
       
       
        
    }
    public void implementGraphic()
    {
        
    }
    public void makeInvisible(JButton j)
    {
        //j.addActionListener(this);
        j.setOpaque(false);
        j.setContentAreaFilled(false);
        j.setBorderPainted(false);
        j.setText("");
       
    }
    public void makeVisible(JButton j)
    {
        j.setOpaque(true);
        j.setContentAreaFilled(true);
        j.setBorderPainted(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton) e.getSource();
        
        if(j == recorder)
        {
            
            if(!record)
            {
                notes = new ArrayList<Note>();
                record = true;
                recorder.setText("Stop");
                this.makeInvisible(save);
                this.makeInvisible(playBack);
                beginTime = System.currentTimeMillis();
                //panel.remove(playBack);
                //panel.remove(save);
            }   
            else
            {
                //panel.add(playBack);
                //panel.add(save);
                this.makeVisible(save);
                this.makeVisible(playBack);
                
                save.setText("Save");
                
                playBack.setText("Play back");
                record = false;
                recorder.setText("Start");
            }
        }
        if(!record && j == save)
        {
            System.out.println("here");
            SaveMusic sm = new SaveMusic(notes, false);
            sm.saveMusic("musicRecorded.txt");
        }
        if(!record && j == playBack)
        {
            System.out.println("here");
            PlayMusic pm = new PlayMusic(notes, false);
            pm.play(1,2);
        }
    }
    public void itemStateChanged(ItemEvent e)
    {
       
            
        if (e.getSource() == beats) { 
            Note n;
            Sound g;
            thread s;
            beat = beats.getSelectedItem() + "";
            //System.out.println(beat);
            if(!beat.equals("None"))
            {
                 
                try{
                     if(beat.contains("Piano"))
                     {
                         n = new Note(0, "PianoBeatCopy.wav", 0);
                         g = new Sound(n, 1, true, 0, 0);
                         s = new thread(g);
                    }
                    else if(beat.contains("Mono"))
                    {
                        n = new Note(0, "Portrait.wav", 0);
                         g = new Sound(n, 1, true, 0, 0);
                         s = new thread(g);
                    }
                    else
                    {
                        n = new Note(0, "Reflect.wav", 0);
                         g = new Sound(n, 1, true, 0, 0);
                         s = new thread(g);
                    }
                    
                     s.start();
                     //System.out.println("main thread" + s);
                     //g.stopClip();
                }
                catch(Exception d)
                {
                    System.out.println("here" + d);
                }
            }
            
        
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        if(map.get(Character.toString(e.getKeyChar())) != null)
        {
            String noteName = map.get(Character.toString(e.getKeyChar())).substring(0,2);
            Long currentTime = System.currentTimeMillis();
            Long length = currentTime - heldButtons.get(noteName);
            int Length = (int) (length / 1);
            int CurrentTime = (int) ((currentTime - beginTime));
        
            if(record)
            {

                notes.add(new Note(Length, noteName, CurrentTime)); 
                System.out.println(notes.get(notes.size()-1));
            }
        
       
            heldButtons.remove(noteName);
            heldButtons1.remove(noteName);
        }
    }
    public void keyPressed(KeyEvent e)
    {
        //System.out.println(e.getKeyChar());
        
        //System.out.println("here");
        
        if(map.get(Character.toString(e.getKeyChar())) != null)
        {
            String noteName = map.get(Character.toString(e.getKeyChar())).substring(0,2);
            if(heldButtons.get(noteName) == null)
            {
                heldButtons.put(noteName, new Long(System.currentTimeMillis()));
            }
            if(heldButtons1.get(noteName) == null)
            {
                heldButtons1.put(noteName, 0);
            }
            //System.out.println("here" + heldButtons1.get("c4"));
        }
    }
    public void keyTyped(KeyEvent e)
    {
    }
    public void initializeLists()
    {
        buttons = new ArrayList<JButton>();
        buttons.add(whiteKeyBase0);
       
        map = new HashMap<String, String>();
        map1 = new HashMap<String, Integer>();
        map.put("a", "c4.wav");
        map.put("s", "d4.wav");
        map.put("d", "e4.wav");
        map.put("f", "f4.wav");
        map.put("g", "g4.wav");
        map.put("h", "a4.wav");
        map.put("j", "b4.wav");
        
        map.put("k", "c5.wav");
        map.put("l", "d5.wav");
        map.put("z", "e5.wav");
        map.put("x", "f5.wav");
        map.put("c", "g5.wav");
        map.put("v", "a5.wav");
        map.put("b", "b5.wav");
        
        map.put("w", "c-4.wav");
        map.put("e", "d-4.wav");
        map.put("t", "f-4.wav");
        map.put("y", "g-4.wav");
        map.put("u", "a-4.wav");
        map.put("o", "c-5.wav");
        map.put("p", "d-5.wav");
        map.put("1", "f-5.wav");
        map.put("2", "g-5.wav");
        map.put("3", "a-5.wav");
        
        map1.put("a", 180);
        map1.put("s", 250);
        map1.put("d", 320);
        map1.put("f", 390);
        map1.put("g", 460);
        map1.put("h", 530);
        map1.put("j", 600);
        
        map1.put("k", 670);
        map1.put("l", 745);
        map1.put("z", 820);
        map1.put("x", 890);
        map1.put("c", 960);
        map1.put("v", 1040);
        map1.put("b", 1110);
        
       
        
        names = new ArrayList<String>();
        names.add("A");
        names.add("S");
        names.add("D");
        names.add("F");
        names.add("G");
        names.add("H");
        names.add("J");
        
        names.add("K");
        names.add("L");
        names.add("Z");
        names.add("X");
        names.add("C");
        names.add("V");
        names.add("B");
        
        names.add("W");
        names.add("E");
        names.add("T");
        names.add("Y");
        names.add("U");
        names.add("O");
        names.add("P");
        names.add("1");
        names.add("2");
        names.add("3");
    }
    public void initializeButtons()
    {
         whiteKeyBase0 = new JButton();
         
    }
    public void setButtonBounds()
    {
          whiteKeyBase0.setBounds(148,174,75,70);
        
    }
    public class playKey extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(map.get(e.getActionCommand()).substring(0,2) != null)
            {
                if(map1.get(e.getActionCommand()) != null)
                {
                    int noteX = map1.get(e.getActionCommand());
                    panel.startTime(noteX, 200);
                
                }
                
                
                
                String noteName = map.get(e.getActionCommand()).substring(0,2);
                //System.out.println(noteName);
                int noteNum = heldButtons1.get(noteName);
                heldButtons1.replace(noteName, noteNum + 1);
                Note example = new Note(10, "c5", 10);
                if(noteNum == 0)
                {
                    
                    Sound s = new Sound(example,0,true,0,0);
                    s.playSoundNormal(map.get(e.getActionCommand()));
                }
        }
        }
    }
}
