package edu.hitsz.propFac;

import edu.hitsz.support.AbstractSupport;
import edu.hitsz.support.Heal;

public class HealFactory extends SupportFactory{
    @Override
    public AbstractSupport CreatSupport(int locationX, int locationY, int speedX, int speedY) {
        return new Heal(locationX, locationY, speedX, speedY);
    }
}
