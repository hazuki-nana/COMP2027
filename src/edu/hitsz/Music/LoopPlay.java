package edu.hitsz.Music;

public class LoopPlay extends Thread{
    private volatile boolean playing = true;
    private volatile boolean isBoss = false;
    private volatile boolean gameOverFlag;
    private final Object lock = new Object();

    private MusicPlay musicPlay;

    public LoopPlay(){
    }
    public void run(){
        while(playing && !gameOverFlag) {
            synchronized (lock){
                try {
                    if (isBoss) {
                        changeMusic();
                        musicPlay = new MusicPlay("bgm_boss");
                    } else {
                        if (musicPlay != null) {
                            changeMusic();
                        }
                        musicPlay = new MusicPlay("bgm");
                    }
                    lock.wait(33000);
                    if (gameOverFlag) {
                        changeMusic();
                        break; // 退出循环
                    }
                } catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        }

    }
    // 停止播放音乐
    public void stopPlaying() {
        playing = false;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public void changeMusic(){
        MusicThread musicThread = musicPlay.getMusicThread();
        musicThread.stopMusic();
    }

    // 更新是否为 Boss
    public void updateIsBoss(boolean isBoss) {
        this.isBoss = isBoss;
        synchronized (lock) {
            lock.notify();
        }
    }

    public void setGameOverFlag(boolean gameOverFlag) {
        this.gameOverFlag = gameOverFlag;
        synchronized (lock){
            lock.notify();
        }
    }
}
