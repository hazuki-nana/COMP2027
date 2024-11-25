package edu.hitsz.airFac;
import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.PlusEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class PlusFactory extends EnemyFactory{
    private int direction(){
        if(Math.random() < 0.5) return 1;
        else return -1;
    }
    @Override
    public AbstractEnemy CreatEnemy() {
        return new PlusEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                3 * direction(),
                5,
                60
        );
    }
}
