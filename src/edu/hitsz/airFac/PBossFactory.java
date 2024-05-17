package edu.hitsz.airFac;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.PlusBoss;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class PBossFactory extends EnemyFactory{

    private double rate = 1;
    public PBossFactory(double rate){
        this.rate = rate;
    }
    @Override
    public AbstractEnemy CreatEnemy() {
        return new PlusBoss(
                (int) (0.5 * Main.WINDOW_WIDTH ),
                (int) (Main.WINDOW_HEIGHT * 0.15),
                0,
                0,
                (int) (500 * (rate))
        );
    }
}
