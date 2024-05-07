package edu.hitsz.aircraft;

import edu.hitsz.BulletTrajectory.Scatter;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.propFac.BombFactory;
import edu.hitsz.propFac.FireFactory;
import edu.hitsz.propFac.HealFactory;
import edu.hitsz.propFac.SupportFactory;
import edu.hitsz.support.AbstractSupport;

import java.util.LinkedList;
import java.util.List;

/**
 * 精英飞机，定时射击子弹
 * @author hitsz
 */
public class PlusEnemy extends AbstractEnemy {

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 3;

    /**
     * 子弹伤害
     */
    private int power = 20;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    public PlusEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        setAbstractTrajectory(new Scatter());
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot()
    {

        return executeShoot(this.getLocationX(), this.getLocationY() + direction * 2,
                this.shootNum, this.power, this.getSpeedY() + direction*3, isHero);
    }
//    public List<BaseBullet> shoot() {
//        double a = 30.0;
//        int x = this.getLocationX();
//        int y = this.getLocationY() + direction*2;
//        int[] speedX = new int [shootNum];
//        int[] speedY = new int [shootNum];
//        for (int i = 0; i < shootNum; i++) {
//            double angle = Math.toRadians(a * (i - 1)); // 将角度转换为弧度
//            speedX[i] = (int) (Math.sin(angle) * 10);
//            speedY[i] =  (int) (Math.cos(angle) * 10);
//        }
//        BaseBullet[] bullet = new BaseBullet[3];
//        for(int i=0; i<shootNum; i++)
//            bullet[i] = new EnemyBullet(x, y, speedX[i], speedY[i], power);
//        return new LinkedList<>(List.of(bullet));
//    }

    public List<AbstractSupport> Drop() {
        return creatDrop(1);
    }

}
