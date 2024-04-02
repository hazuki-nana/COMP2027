package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.support.AbstractSupport;
import edu.hitsz.support.Bomb;
import edu.hitsz.support.Fire;
import edu.hitsz.support.Heal;

import java.util.LinkedList;
import java.util.List;

/**
 * 精英飞机，定时射击子弹
 * @author hitsz
 */
public class EliteEnemy extends AbstractAircraft {

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

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
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
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
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*3;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }

    public List<AbstractSupport> Drop(){
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = direction*2;
        int speedY = direction*2;
        List<AbstractSupport> res = new LinkedList<>();
        if (Math.random()< 0.6) {
            AbstractSupport support;
            if (Math.random() < 0.4)
                support = new Heal(x, y, speedX, speedY);
            else if (Math.random() < 0.6)
                support = new Bomb(x, y, speedX, speedY);
            else
                support = new Fire(x, y, speedX, speedY);
            res.add(support);
        }
        return res;
    }

}