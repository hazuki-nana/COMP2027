package edu.hitsz.application;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import edu.hitsz.Graph.Rank;
import edu.hitsz.Graph.Start;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    public static final CardLayout cardLayout = new CardLayout(0, 0);
    public static final JPanel cardPanel = new JPanel(cardLayout);

    public static void displayOver(int score, String diff){
        try {
            cardPanel.add(new Rank(score, diff).getMainPanel());
            cardLayout.last(cardPanel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("StartMenu");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cardPanel);

        Start start = new Start();
        cardPanel.add(start.getMainPanel());

        frame.setVisible(true);

    }
}
