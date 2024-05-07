package Graph;

import edu.hitsz.DAO.DaoImpl;
import edu.hitsz.DAO.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rank {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel buttomPanel;
    private JLabel Header;
    private JTable rankTable;
    private JButton Delete;
    private JScrollPane rankScroll;

    private final DaoImpl dao = new DaoImpl();

    private ArrayList<String[]> InnData = new ArrayList<>();
    public Rank() throws IOException {
        addData(12);
        readData();
        String[] columnName = {"名次", "姓名", "分数", "时间"};
        String[][] Data = InnData.toArray(new String[0][0]);

        DefaultTableModel model = new DefaultTableModel(Data, columnName){
            @Override
            public boolean isCellEditable(int row, int col){return false;};
        };
        rankTable.setModel(model);
        rankScroll.setViewportView(rankTable);

        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = rankTable.getSelectedRow();
                System.out.println(row);
                int result = JOptionPane.showConfirmDialog(Delete,"是否确定删除?");
                System.out.println(result);
                if(JOptionPane.YES_OPTION == result && row != -1){
                    model.removeRow(row);
                    InnData.remove(row);
                    try {
                        updateData();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    private void readData() throws IOException {
        List<Player> Players = dao.getAllScores();
        Collections.sort(Players);
        int i = 0;
        for (Player py : Players){
            String[] splits = py.toString().split("  ");
            InnData.add(new String[]{String.valueOf(++i), splits[0], splits[1], splits[2]});
        }
    }

    private void updateData() throws IOException{

        List<Player> players = new ArrayList<>();
        for(String[] sigData : InnData){
            Player player = new Player(sigData[1], Integer.valueOf(sigData[2]), sigData[3]);
            System.out.println(player.toString());
            players.add(player);
        }
        dao.updateScore(players);
    }

    private void addData(int score) throws IOException {
        String username = JOptionPane.showInputDialog(mainPanel, "游戏结束，您的得分为：" + score + "\n请输入你的用户名：");
        if (username == null) { username = "default";}
        String time = "123456";
        Player player = new Player(username, score, time);
        dao.addScore(player);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Rank");
        frame.setSize(512, 768);
        frame.setContentPane(new Rank().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
