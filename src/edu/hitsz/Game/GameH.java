package edu.hitsz.Game;

import edu.hitsz.airFac.BossFactory;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import java.awt.*;

public class GameH extends BaseGame{
    /**
     * 屏幕中出现的敌机最大数量
     */

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

    private double gameAugment = 0.006;
    private double bossAugment = 0.03;
    private double bossRate = 1.2;

    public GameH(){
        super();
        setDiff("Hard");
    }

    @Override
    public void eliteChange() {
        super.probability -= gameAugment;
        super.enemyMaxNumber += 2;
    }

    @Override
    public void bossChange() {
        super.enemyFactory = new BossFactory(bossRate);
        bossRate += bossAugment;
        System.out.print(" boss血量：" + (500 * (bossRate)));
    }

    @Override
    public void setCycleDuration() {
        if (super.cycleDuration >= 400){
            super.cycleDuration -= 50;
        }
    }

    @Override
    public void setThreshold() {
        if (super.cycleDuration >= 400){
            super.threShold -= 50;
        }
    }


    @Override
    public void paint(Graphics g) {

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE5, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE5, 0, this.backGroundTop, null);
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
