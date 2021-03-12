import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class MainTheme extends Audio {

    public MainTheme() throws LineUnavailableException, MalformedURLException, IOException, UnsupportedAudioFileException {
        init(getClass().getResource("IzzyPiano.wav"));
    }

}
