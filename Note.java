public class Note
{
    int length;
    String name;
    int soundingTime;
    public Note(int Length, String Name, int SoundingTime)
    {
        length = Length;
        name = Name;
        soundingTime = SoundingTime;
    }
    public String toString()
    {
        String s = "Name: "+name+", Length: " +length+", Timing: " + soundingTime;
        return s;
    }
    public String format()
    {
        String s = length + name + soundingTime;
        return s;
    }
}
