package edu.hitsz.Music;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.DataLine.Info;

public class Test extends Thread {
    private String filename;
    private AudioFormat audioFormat;
    private byte[] samples;
    private volatile boolean playing;

    public Test(String filename) {
        this.filename = filename;
        reverseMusic();
        playing = false;
    }

    public void reverseMusic() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename));
            audioFormat = stream.getFormat();
            samples = getSamples(stream);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getSamples(AudioInputStream stream) {
        int size = (int) (stream.getFrameLength() * audioFormat.getFrameSize());
        byte[] samples = new byte[size];
        try {
            DataInputStream dataInputStream = new DataInputStream(stream);
            dataInputStream.readFully(samples);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return samples;
    }

    public void play(InputStream source) {
        int size = (int) (audioFormat.getFrameSize() * audioFormat.getSampleRate());
        byte[] buffer = new byte[size];
        SourceDataLine dataLine = null;
        Info info = new Info(SourceDataLine.class, audioFormat);
        try {
            dataLine = (SourceDataLine) AudioSystem.getLine(info);
            dataLine.open(audioFormat, size);
            dataLine.start();
            int numBytesRead;
            while (playing && (numBytesRead = source.read(buffer, 0, buffer.length)) != -1) {
                dataLine.write(buffer, 0, numBytesRead);
            }
            dataLine.drain();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        } finally {
            if (dataLine != null) {
                dataLine.close();
            }
        }
    }

    public void stopMusic() {
        playing = false;
    }

    @Override
    public void run() {
        playing = true;
        InputStream stream = new ByteArrayInputStream(samples);
        play(stream);
    }
}
