package edu.hitsz.propFac;

import edu.hitsz.support.AbstractSupport;
import edu.hitsz.support.Bomb;

public class BombFactory extends SupportFactory{
    @Override
    public AbstractSupport CreatSupport(int locationX, int locationY, int speedX, int speedY) {
        return new Bomb(locationX, locationY, speedX, speedY);
    }
}
