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

public class Graphic extends JPanel implements ActionListener
{
    Timer time = new Timer(10, this);
    boolean done = false;
    int x = 400;
    int y = 300;
    int newPointX = 400;
    int newPointY = 300;
    Image img;
    JButton butt;
    boolean first = true;
    int quadrant = 0;
    int[] xArray = new int[5];
    int[] yArray = new int[5];
    ArrayList<Point> points = new ArrayList<Point>();
    public Graphic(Image Img)
    {
         time.start();
         img = Img;
         //this.createPoints(0);
        //butt = new JButton();
        //butt.setText("HI");
        //butt.setBounds(100,100,100,100);
        //this.add(butt);
        /*
        for(int i = 0; i < 1; i++)
        {
        
        System.out.println("x: " + newPointX);
        System.out.println("y: " + newPointY);
        }
        */
    }
    public void paintComponent(Graphics g) 
    {
        super.paintComponents(g);
        
        g.drawImage(img,150,26,this);
            
        Graphics2D g2D = (Graphics2D) g;
        //this.setBackground(new Color(111, 209, 160));
      //System.out.println(points.size());
      
           for(int i = 0; i < points.size(); i++)
           {
           
          
           
               Point p = points.get(i);
               g2D.setColor(p.color);
               //System.out.println(p.currentLocationX);
               g2D.fillOval(p.currentLocationX, p.currentLocationY, 10, 10);
           
           }
           
               
       
       
    }
    public void startTime(int X, int Y)
    {
        //done = false;
        //System.out.println(true);
        x = X;
        y = Y;
        this.createPoints();
        
        //double startTime = System.currentTimeMillis();
        
        //done = true;
    }
    public void actionPerformed(ActionEvent e)
    {
        for(int i = 0; i < points.size(); i++)
        {
            Point p = points.get(i);
            if(p.lifeSpan == 0)
            {
                points.remove(i);
                i--;
            }
        }

        for(int i = 0; i < points.size(); i++)
        {
                Point p = points.get(i);
                p.lifeSpan--;
            //System.out.println("here");
           
               // p.counter = 0;
                if(p.desiredLocationX > p.currentLocationX)
                {
                    
                    p.currentLocationX += 1;
                }
                else if(p.currentLocationX > p.desiredLocationX)
                {
                    p.currentLocationX-=1;
                }
             
                if(p.desiredLocationY > p.currentLocationY)
                {
                    p.currentLocationY+=1;
                    //System.out.println("here");
                }
                else if(p.currentLocationY > p.desiredLocationY)
                {
                    p.currentLocationY-=1;
                } 
        }
        repaint(); 
    }   
    public void createPoints()
    {   /*
        for(int i = 0; i < x; i++)
        {
            int z = (int) (Math.random() * 360);
            quadrant = (int) (z/90);
            quadrant++;
            int normAngle = 0;
            //int q = (int) ((Math.random() * 100) + 50);
            int q = 100;
            /*
            if(quadrant == 1) {
               
                normAngle = z;
                newPointX = (int) Math.abs((q * Math.sin(normAngle)));
                newPointY = (int) Math.abs((q * Math.cos(normAngle)));
            }
            else if(quadrant == 2) {
                normAngle = z - 90;
                newPointX = (int) Math.abs(q * Math.sin(normAngle));
                newPointY = (int) Math.abs(q * Math.cos(normAngle));
                newPointX = newPointX * (-1);
            }
            else if(quadrant == 3) {
                
                normAngle = z - 180;
                newPointX =  (int) Math.abs((q * Math.sin(normAngle)));
                newPointY =  (int) Math.abs((q * Math.cos(normAngle)));
                newPointY = newPointY * (-1);
                newPointX = newPointX * (-1);
            }
            else {
                
                normAngle = z - 270;
                newPointX = (int) Math.abs((q * Math.sin(normAngle)));
                newPointY = (int) Math.abs((q * Math.cos(normAngle)));
                newPointY = newPointY * (-1);
            }
            int ySlope = (y-(newPointY + y));
            int xSlope = (x-(newPointX + x));
            double ratio = ySlope/xSlope;
            */
            
           Color[] colors = new Color[8];
           for(int i = 0; i < colors.length; i++)
           {
               colors[i] = new Color((int) (Math.random()*256), (int) (Math.random()*256), (int) (Math.random()*256));
           }
            
         //points = new ArrayList<Point>();
       // for(int i = 0; i < points.size(); i++)
        //{
          //  points.remove(i);
       // }
       if(!first)
       {
        points.add(0, new Point(x,y, x + 53, y + 53, colors[0], 100));
        points.add(1, new Point(x,y, x - 53, y + 53, colors[1], 100));
        points.add(2, new Point(x,y, x + 53, y - 53, colors[2], 100));
        points.add(3, new Point(x,y, x - 53, y - 53, colors[3], 100));
        points.add(4, new Point(x,y, x + 75, y, colors[4], 100));
        points.add(5, new Point(x,y, x - 75, y, colors[5], 100));
        points.add(6, new Point(x,y, x, y + 75, colors[6], 100));
        points.add(7, new Point(x,y, x, y - 75, colors[7], 100));
        //time.start();
    }
    else
    {
        first = false;
    }
            
            
    }
}

