package edu.hitsz.aircraft;

import edu.hitsz.BulletTrajectory.Circle;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.support.AbstractSupport;

import java.util.List;

public class Battery extends AbstractEnemy{
    /**
     * 子弹一次发射数量
     */
    private int shootNum = 20;

    /**
     * 子弹伤害
     */
    private int power = 5;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;
    public Battery(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        setAbstractTrajectory(new Circle());
        score = 15;
    }

    @Override
    public List<BaseBullet> shoot() {
        return executeShoot(this.getLocationX(), this.getLocationY() + direction * 2,
                this.shootNum, this.power, this.getSpeedY(), isHero);
    }

    @Override
    public List<AbstractSupport> Drop() {
        return List.of();
    }

    @Override
    public void update() {
        this.vanish();
    }
}
