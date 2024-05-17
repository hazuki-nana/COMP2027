package edu.hitsz.propFac;

import edu.hitsz.support.AbstractSupport;
import edu.hitsz.support.MateProp;

public class MateSupFactory extends SupportFactory{
    @Override
    public AbstractSupport CreatSupport(int locationX, int locationY, int speedX, int speedY) {
        return new MateProp(locationX, locationY, speedX, speedY);
    }
}
