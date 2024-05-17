package edu.hitsz.Game;

import edu.hitsz.airFac.BossFactory;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import java.awt.*;

public class GameS extends BaseGame{

    /**
     * 当前得分
     */
    private int score = 0;
    /**
     * 当前时刻
     */
    private int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;


    public GameS(){
        super();
        setDiff("Simple");
    }

    @Override
    public boolean isSimple(){
        return true;
    }
    @Override
    public void eliteChange() {

    }

    @Override
    public void bossChange() {

    }

    @Override
    public void setCycleDuration() {

    }

    @Override
    public void setThreshold() {

    }

    @Override
    public void setBatteryTime() {

    }


    @Override
    public void paint(Graphics g) {

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE2, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE2, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, supports);
        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }
}
