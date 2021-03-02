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
public class Point
{
    int currentLocationX;
    int currentLocationY;
    int desiredLocationX;
    int desiredLocationY;
    Color color;
    int lifeSpan;
    public Point(int CurrentLocationX, int CurrentLocationY, int DesiredLocationX, int DesiredLocationY, Color color1, int LifeSpan)
    {
        currentLocationX = CurrentLocationX;
        currentLocationY = CurrentLocationY;
        desiredLocationX = DesiredLocationX;
        desiredLocationY = DesiredLocationY;
        color = color1;
        lifeSpan = LifeSpan;
    }
    
    
}
