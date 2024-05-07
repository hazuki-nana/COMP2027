package Game;

import javax.swing.*;
import java.awt.*;

public class GameChoosing {
    private final JPanel mainPanel;
    private int diff;
    public GameChoosing(int diff){
        this.diff = diff;
        mainPanel = new JPanel();
        BaseGame game = null;
        if (diff == 1){
            System.out.println("Hello Aircraft War Simple");
            game = new GameS();
        }
        else if(diff == 2){
            System.out.println("Hello Aircraft War Normal");
            game = new GameN();
        }
        else {
            System.out.println("Hello Aircraft War Hard");
            game = new GameH();
        }
        game.setPreferredSize(new Dimension(512, 768));
        mainPanel.add(game);
        game.startGame();
    }


    public JPanel getMainPanel(){
        return mainPanel;
    }
}
