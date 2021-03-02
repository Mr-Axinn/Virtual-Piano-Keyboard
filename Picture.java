import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Color;
public class Picture
{
    Pixel[][] pixels;
    Pixel[][] copiedPixels;
    BufferedImage img;
    public Picture()
    {
        try {
            img = ImageIO.read(new File("ActualPiano copy.jpg"));
        } catch (IOException e) {
            System.out.println(e);
        }
        pixels = new Pixel[img.getWidth()][img.getHeight()];
        for(int i = 0; i < pixels.length; i++) {
            for(int j = 0; j < pixels[0].length; j++) {
                int x = img.getRGB(i,j);
                pixels[i][j] = new Pixel(i,j, getRed(x), getGreen(x), getBlue(x));
            } 
        }
        copiedPixels = pixels;
    }
    public void changePicture(int x, int y, int width, int height) {
        Color red = new Color(240, 50, 60);
        System.out.println(x);
        System.out.println(y);
        System.out.println(width);
        System.out.println(height);
        for(int i = 0; i < img.getWidth(); i++) {
            for(int j = 0; j < img.getHeight(); j++) {
                if(i < 60)
                {
                    copiedPixels[i][j].setColor(red);
                    img.setRGB(i,j,copiedPixels[i][j].getValue());
                }
            }
        }
    }
    public Image getImage()
    {
        return img;
    }
    public static int getGreen(int value) {
        int green = (value >> 8) & 0xff;
        return green;
    }   
    public static int getRed(int value) {
        int red = (value >> 16) & 0xff;
        return red;
    }
    public static int getBlue(int value)
    {
        int blue = value & 0xff;
        return blue;
    }
}
