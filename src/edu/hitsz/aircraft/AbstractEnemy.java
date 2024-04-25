package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.support.AbstractSupport;

import java.util.List;

public abstract class AbstractEnemy extends AbstractAircraft{

    boolean isHero = false;
    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public abstract List<BaseBullet> shoot();

    @Override
    public abstract List<AbstractSupport> Drop();
}
