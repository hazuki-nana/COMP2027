package edu.hitsz.Music;

public class MusicPlay {

    private String Folder = "src/videos/";
    private String filename;
    private String filepath;
    private MusicThread musicThread;
    public MusicThread getMusicThread() {
        return musicThread;
    }

    public MusicPlay(String filename) {
        this.filename = filename;
        filepath = Folder + filename + ".wav";
        musicThread = new MusicThread(filepath);
        musicThread.start();
    }

}
