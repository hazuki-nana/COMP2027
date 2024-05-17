package edu.hitsz.airFac;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossFactory extends EnemyFactory{
    private double rate = 1;
    private int direction(){
        if(Math.random() < 0.5) return 1;
        else return -1;
    }

    public BossFactory(double rate){
        this.rate = rate;
    }
    @Override
    public AbstractEnemy CreatEnemy() {
        return new BossEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                3 * direction(),
                0,
                (int)(500 * (rate))
        );
    }
}
