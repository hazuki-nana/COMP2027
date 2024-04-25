package edu.hitsz.BulletTrajectory;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class Circle extends AbstractTrajectory{

    @Override
    public List<BaseBullet> shoot(int locationX, int locationY, int shootNum, int power, int LocspeedY, boolean isHero) {
        double a = (double) 360 / shootNum ;
        int[] speedX = new int[shootNum];
        int[] speedY = new int[shootNum];
        for (int i = 0; i < shootNum; i++) {
            double angle = Math.toRadians(a * (i)); // 将角度转换为弧度
            speedX[i] = (int) (Math.sin(angle) * 20);
            speedY[i] = (int) (Math.cos(angle) * 10);
        }
        BaseBullet[] bullet = new BaseBullet[shootNum];
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            if (!isHero)
                bullet[i] = new EnemyBullet(locationX, locationY, speedX[i], speedY[i], power);
            else
                bullet[i] = new HeroBullet(locationX, locationY, speedX[i], speedY[i], power);
        }
        return new LinkedList<>(List.of(bullet));
    }
}
