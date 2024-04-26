package edu.hitsz.BulletTrajectory;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface AbstractTrajectory {
    public abstract List<BaseBullet> shoot(int locationX, int locationY, int shootNum, int power, int LocspeedY, boolean isHero);
}
