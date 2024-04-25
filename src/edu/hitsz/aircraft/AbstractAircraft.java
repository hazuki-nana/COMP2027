package edu.hitsz.aircraft;

import edu.hitsz.BulletTrajectory.AbstractTrajectory;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.support.AbstractSupport;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;
    private AbstractTrajectory abstractTrajectory;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public int getHp() {
        return hp;
    }

    public void setAbstractTrajectory(AbstractTrajectory abstractTrajectory)
    {
        this.abstractTrajectory = abstractTrajectory;
    }

    public List<BaseBullet> executeShoot(int locationX, int locationY, int shootNum, int power, int LocspeedY, boolean isHero)
    {
        return abstractTrajectory.shoot(locationX, locationY, shootNum, power, LocspeedY, isHero);
    }
    /**
     * 飞机射击方法，可射击对象必须实现
     * @return
     *  可射击对象需实现，返回子弹
     *  非可射击对象空实现，返回null
     */
    public abstract List<BaseBullet> shoot();
}


