import java.awt.Color;
public class Pixel
{
    //Help from ericson@cc.gatech.edu pix lab from last year's AP
    int y;
    int x;
    int red;
    int green;
    int blue;
    int alpha;
    public Pixel(int X, int Y, int Red, int Green, int Blue) {
        y = Y;
        x = X;
        red = Red;
        blue = Blue;
        green = Green;
        alpha = 255;
    }
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }
    public void setColor(Color newColor) 
    {
        red = newColor.getRed();
        green = newColor.getGreen();
        blue = newColor.getBlue();
        //updatePicture(this.getAlpha(),red,green,blue);
    }
    public int getValue()
    {
        int value = (alpha << 24) + (red << 16) + (green << 8) + blue;
        return value;
    }
}
