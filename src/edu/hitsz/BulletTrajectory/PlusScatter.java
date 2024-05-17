package edu.hitsz.BulletTrajectory;

import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

public class PlusScatter extends Scatter{
    private List<BaseBullet> bullets = new LinkedList<>();
    @Override
    public List<BaseBullet> shoot(int locationX, int locationY, int shootNum, int power, int LocspeedY, boolean isHero) {
         for (int i = -1; i<2; i++){
             bullets.addAll(super.shoot(locationX - i * 100, locationY, shootNum, power, LocspeedY, isHero));
         }
         return bullets;
    }
}
