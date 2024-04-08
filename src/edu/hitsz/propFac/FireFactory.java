package edu.hitsz.propFac;

import edu.hitsz.support.AbstractSupport;
import edu.hitsz.support.Fire;

public class FireFactory extends SupportFactory{
    @Override
    public AbstractSupport CreatSupport(int locationX, int locationY, int speedX, int speedY) {
        return new Fire(locationX, locationY, speedX, speedY);
    }
}
