package edu.hitsz.DAO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class test {
    DaoImpl dao = new DaoImpl("Hard");

    public test() throws IOException {
    }

    public List<Player> getAllScores() throws IOException {
        return dao.getAllScores();
    }

    public List<Player> getScore(String username) throws IOException
    {
        return dao.getScore(username);
    }

    public void addScore(Player score) throws IOException{
        dao.addScore(score);
    }
    public static void main(String[] args) throws IOException {
        test T = new test();
        for(Player s : T.getAllScores())
            System.out.println(s.toString());
        System.out.println(T.getScore("casd").toString());
        T.addScore(new Player("#####", 23, "51"));
        System.out.println(T.getScore("456").toString());
        System.out.println(T.getAllScores());
        System.out.println("////////");
        List<Player> scores = T.getAllScores();
        Collections.sort(scores);
        System.out.println(scores);
    }
}
