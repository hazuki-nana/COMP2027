package edu.hitsz.Graph;

import edu.hitsz.Game.GameChoosing;
import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JPanel{
    private JPanel mainPanel;
    private JButton ButtonS;
    private JButton ButtonN;
    private JButton ButtonH;
    private JComboBox<String> AcousticCombo;
    public static boolean isSoundOn = true;

    public Start(){
        String[] acouSel = {"On", "Off"};
        AcousticCombo.addItem("On");
        AcousticCombo.addItem("Off");
        Font font = new Font("Arial", Font.BOLD, 14);
        AcousticCombo.setFont(font);
        AcousticCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choose = AcousticCombo.getSelectedItem().toString();
                isSoundOn = !choose.equals("Off");
                System.out.println(isSoundOn+choose);
            }
        });
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Start");
        frame.setContentPane(new Start().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
