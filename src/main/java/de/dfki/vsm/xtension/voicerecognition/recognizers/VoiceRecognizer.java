package de.dfki.vsm.xtension.voicerecognition.recognizers;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

/**
 * Created by alvaro on 5/22/17.
 */
public interface VoiceRecognizer extends Runnable {
    void startRecording() throws IOException, LineUnavailableException;

    void stopRecording();
}
