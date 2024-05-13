package edu.hitsz.support;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.basic.AbstractFlyingObject;

import java.util.List;

public class Heal extends AbstractSupport {

    protected int hp;

    public Heal(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
        hp = 10;
    }
    public void Effect(HeroAircraft heroAircraft){
        heroAircraft.increaseHp(hp);
    }

}
