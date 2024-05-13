package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.propFac.*;
import edu.hitsz.support.AbstractSupport;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractEnemy extends AbstractAircraft{

    int score;
    boolean isHero = false;
    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    public List<AbstractSupport> creatDrop(int num){
        int x = this.getLocationX();
        int y = this.getLocationY();
        int speedX = 2;
        int speedY = 4;
        SupportFactory support;
        int propnum = (int)(Math.random() * (num+1));
        AbstractSupport[] slist = new AbstractSupport[propnum];
        for (int i = 0; i < propnum; i++){
            if (Math.random() < 0.3)
                support = new HealFactory();
            else if (Math.random() < 0.5)
                support = new BombFactory();
            else if (Math.random() < 0.7)
                support = new FireFactory();
            else
                support = new PlusFireFactory();
            slist[i] = support.CreatSupport(x + (i*2 - propnum + 1)*10, y, speedX, speedY);
        }
        return new LinkedList<>(List.of(slist));
    }

    @Override
    public abstract List<BaseBullet> shoot();

    public abstract List<AbstractSupport> Drop();
}
