package edu.hitsz.BulletTrajectory;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class Direct implements AbstractTrajectory{

    @Override
    public List<BaseBullet> shoot(int locationX, int locationY, int shootNum, int power, int LocspeedY, boolean isHero) {
        List<BaseBullet> res = new LinkedList<>();
        int speedX = 0;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            if (!isHero)
                bullet = new EnemyBullet(locationX + (i*2 - shootNum + 1)*10, locationY, speedX, LocspeedY, power);
            else
                bullet = new HeroBullet(locationX + (i*2 - shootNum + 1)*10, locationY, speedX, LocspeedY, power);
            res.add(bullet);
        }
        return res;
    }
}
