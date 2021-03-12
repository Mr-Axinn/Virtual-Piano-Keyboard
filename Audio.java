import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//Source for most of this code goes to some random guy on stack overflow
public class Audio {

    private Clip clip;

    protected Audio() {
    }

    public Audio(File source) throws LineUnavailableException, MalformedURLException, IOException, UnsupportedAudioFileException {
        this(source.toURI().toURL());
    }

    public Audio(URL source) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        this(source.openStream());
    }

    public Audio(InputStream source) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        init(source);
    }

    protected void init(File source) throws LineUnavailableException, MalformedURLException, IOException, UnsupportedAudioFileException {
        init(source.toURI().toURL());
    }

    protected void init(URL source) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        init(source.openStream());
    }

    protected void init(InputStream source) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(source));
    }

    public void reset() {
        clip.stop();
        clip.setFramePosition(0);
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public boolean isPlaying() {
        return clip.isActive();
    }
}