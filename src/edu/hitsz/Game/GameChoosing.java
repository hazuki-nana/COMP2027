package edu.hitsz.Game;

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
            System.out.println("Simple Mode");
            game = new GameS();
        }
        else if(diff == 2){
            System.out.println("Normal Mode");
            game = new GameN();
        }
        else {
            System.out.println("Hard Mode");
            game = new GameH();
        }
        game.setPreferredSize(new Dimension(512, 768));
        mainPanel.add(game);
        game.action();
    }


    public JPanel getMainPanel(){
        return mainPanel;
    }
}
