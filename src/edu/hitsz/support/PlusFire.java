package edu.hitsz.support;

import edu.hitsz.aircraft.HeroAircraft;

public class PlusFire extends AbstractSupport {

    public PlusFire(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    public void Effect(HeroAircraft heroAircraft){
        heroAircraft.increasePlusFire();
    }

}
