package edu.hitsz.BulletTrajectory;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class Scatter implements AbstractTrajectory{

    @Override
    public List<BaseBullet> shoot(int locationX, int locationY, int shootNum, int power, int LocspeedY, boolean isHero) {
        double a = 30;
        int[] speedX = new int [shootNum];
        int[] speedY = new int [shootNum];
        for (int i = 0; i < shootNum; i++) {
            double angle = Math.toRadians(a * (i - shootNum / 2)); // 将角度转换为弧度
            speedX[i] = (int) (Math.sin(angle) * 5);
            speedY[i] =  (int) (Math.cos(angle) * 10);
        }
        BaseBullet[] bullet = new BaseBullet[shootNum];
        for(int i=0; i<shootNum; i++)
            if (!isHero)
                bullet[i] = new EnemyBullet(locationX, locationY, speedX[i], speedY[i], power);
            else
                bullet[i] = new HeroBullet(locationX, locationY, speedX[i], speedY[i] * -1, power);
        return new LinkedList<>(List.of(bullet));
    }
}
