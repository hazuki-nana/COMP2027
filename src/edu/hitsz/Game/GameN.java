package edu.hitsz.Game;

import edu.hitsz.airFac.BossFactory;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import java.awt.*;

public class GameN extends BaseGame{


    private double gameAugment = 0.003;
    private double bossAugment = 0.01;
    private double bossRate = 1;
    public GameN(){
        super();
        setDiff("Normal");
    }

    @Override
    public void eliteChange() {
        super.probability -= gameAugment;
        super.enemyMaxNumber += 1;
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
            super.cycleDuration -= 30;
        }
    }

    public void setThreshold() {
        if (super.cycleDuration >= 400){
            super.threShold -= 20;
        }
    }

    @Override
    public void setBatteryTime() {

    }

    @Override
    public void setMateTime() {

    }

    @Override
    public void paint(Graphics g) {

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE3, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE3, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, supports);
        paintImageWithPositionRevised(g, divineSupports);
        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g, mateAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }
}
