package edu.hitsz.support;

import edu.hitsz.aircraft.HeroAircraft;

public class Fire extends AbstractSupport {

    public Fire(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    public void Effect(HeroAircraft heroAircraft){
        heroAircraft.increaseFire(1);
    }

}
