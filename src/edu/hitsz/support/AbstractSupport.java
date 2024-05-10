package edu.hitsz.support;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

import java.util.List;

public abstract class AbstractSupport extends AbstractFlyingObject {
    public AbstractSupport(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }


    public abstract void Effect(HeroAircraft heroAircraft, List<AbstractEnemy> enemyList);

}
