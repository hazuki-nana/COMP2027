package edu.hitsz.aircraft;

import edu.hitsz.BulletTrajectory.Scatter;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.support.AbstractSupport;

import java.util.List;

public class Mate extends AbstractAircraft{
    /**
     * 子弹一次发射数量
     */
    private int shootNum = 5;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = -1;

    boolean isHero = true;

    public Mate(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        setAbstractTrajectory(new Scatter());
    }

    @Override
    public List<BaseBullet> shoot() {
        return executeShoot(this.getLocationX(), this.getLocationY() + direction * 2,
                this.shootNum, this.power, this.getSpeedY() + direction*10, isHero);
    }

}
