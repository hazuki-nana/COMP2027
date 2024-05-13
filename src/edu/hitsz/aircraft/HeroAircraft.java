package edu.hitsz.aircraft;

import edu.hitsz.BulletTrajectory.Circle;
import edu.hitsz.BulletTrajectory.Direct;
import edu.hitsz.BulletTrajectory.Scatter;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.Music.MusicPlay;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 2;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = -1;

    private boolean isHero = true;

    /**
     *构造单例
     */
    private volatile static HeroAircraft heroAircraft;
    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        setAbstractTrajectory(new Direct());
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot()
    {
        return executeShoot(this.getLocationX(), this.getLocationY() + direction * 2,
                this.shootNum, this.power, this.getSpeedY() + direction*10, isHero);
    }

    /**
     * 道具：回复血量
     */
    public void increaseHp(int hp){
        this.hp += hp;
        if (this.hp>maxHp)
            this.hp=maxHp;
    }

    /**
     * 道具：火力增加
     */
    public void increaseFire(){
        this.shootNum = 3;
        setAbstractTrajectory(new Scatter());

        new Thread(new Runnable () {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                finally {
                    reverseFire();
                }
            }
        }).start();
    }
    /**
     * 道具：超级火力增加
     */
    public void increasePlusFire(){
        this.shootNum = 20;
        setAbstractTrajectory(new Circle());

        new Thread(new Runnable () {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                finally {
                    reverseFire();
                }
            }
        }).start();

    }

    /**
     * 道具：还原
     */
    private void reverseFire(){
        this.shootNum = 2;
        setAbstractTrajectory(new Direct());
    }




    public static HeroAircraft getHeroAircraft() {
        if (heroAircraft == null){
            synchronized (HeroAircraft.class){
                if (heroAircraft == null) {
                heroAircraft = new HeroAircraft(
                        Main.WINDOW_WIDTH / 2,
                        Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                        0, 0, 300);
                }
            }
        }
        return heroAircraft;
    }
}
