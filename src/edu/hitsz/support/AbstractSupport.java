package edu.hitsz.support;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

public abstract class AbstractSupport extends AbstractFlyingObject {
    public AbstractSupport(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if ( locationX<=0 || locationX>=Main.WINDOW_WIDTH || locationY<=0 || locationY>=Main.WINDOW_HEIGHT){
            this.vanish();
        }
    }

    public abstract void Effect(HeroAircraft heroAircraft);

}
