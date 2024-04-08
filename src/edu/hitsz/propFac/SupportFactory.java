package edu.hitsz.propFac;

import edu.hitsz.support.AbstractSupport;

public abstract class SupportFactory {
    public abstract AbstractSupport CreatSupport(int locationX, int locationY, int speedX, int speedY);
}
