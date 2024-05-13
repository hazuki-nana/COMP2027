package edu.hitsz.Music;

import edu.hitsz.Graph.Start;

public class MusicPlay {

    private String Folder = "src/videos/";
    private String filename;
    private String filepath;
    private MusicThread musicThread;
    public MusicThread getMusicThread() {
        return musicThread;
    }

    public MusicPlay(String filename, boolean isloop) {
        if (Start.isSoundOn){
            this.filename = filename;
            filepath = Folder + filename + ".wav";
            musicThread = new MusicThread(filepath, isloop);
            musicThread.start();
        }
    }

}
