package edu.hitsz.support;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;

import java.util.List;

public class MateProp extends AbstractSupport{
    public MateProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void Effect(HeroAircraft heroAircraft) {

    }

    public void Effect(HeroAircraft heroAircraft, List<AbstractAircraft> mateAircrafts){
        heroAircraft.Summon(mateAircrafts);
    }
}
