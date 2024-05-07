package Graph;

import Game.GameChoosing;
import Game.GameH;
import Game.GameS;
import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
    private JPanel mainPanel;
    private JButton ButtonS;
    private JButton ButtonN;
    private JButton ButtonH;
    private JComboBox comboBox1;


    public Start(){
        ButtonS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.cardPanel.add(new GameChoosing(1).getMainPanel());
                Main.cardLayout.last(Main.cardPanel);
            }
        });

        ButtonN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.cardPanel.add(new GameChoosing(2).getMainPanel());
                Main.cardLayout.last(Main.cardPanel);
            }
        });

        ButtonH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.cardPanel.add(new GameChoosing(3).getMainPanel());
                Main.cardLayout.last(Main.cardPanel);
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

}
