package edu.hitsz.Music;

public class LoopPlay extends Thread{
    private boolean isBoss = false;
    private MusicPlay musicPlay;

    public LoopPlay(){
    }
    public void run(){
        if (isBoss) {
            stopMusic();
            musicPlay = new MusicPlay("bgm_boss", true);
        } else {
            if (musicPlay != null) {
                stopMusic();
            }
            musicPlay = new MusicPlay("bgm", true);
        }
    }

    public void stopMusic(){
        MusicThread musicThread = musicPlay.getMusicThread();
        musicThread.stopMusic();
    }

    // 更新是否为 Boss
    public void updateIsBoss() {
        this.isBoss = !isBoss;
        stopMusic();
        run();
    }

}
