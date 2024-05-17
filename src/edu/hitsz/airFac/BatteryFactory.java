package edu.hitsz.airFac;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.Battery;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BatteryFactory extends EnemyFactory{

    @Override
    public AbstractEnemy CreatEnemy() {
        return new Battery(
                (int) (Math.random() * (Main.WINDOW_WIDTH)),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.5),
                0,
                0,
                20
        );
    }
}
