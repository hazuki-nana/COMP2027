package edu.hitsz.airFac;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.Mate;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MateFactory {
    public AbstractAircraft creatMate() {
        return new Mate(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                Main.WINDOW_HEIGHT,
                0,
                -4,
                30
        );
    }

}
