package edu.hitsz.support;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.HeroAircraft;

import java.util.List;

public class Bomb extends AbstractSupport {

    public Bomb(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    public void Effect(HeroAircraft heroAircraft, List<AbstractEnemy> enemyList){
        heroAircraft.Explode(enemyList);
    }

}
