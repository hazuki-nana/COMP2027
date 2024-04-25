package edu.hitsz.propFac;

import edu.hitsz.support.AbstractSupport;
import edu.hitsz.support.Heal;
import edu.hitsz.support.PlusFire;

public class PlusFireFactory extends SupportFactory{
    @Override
    public AbstractSupport CreatSupport(int locationX, int locationY, int speedX, int speedY) {
        return new PlusFire(locationX, locationY, speedX, speedY);
    }
}