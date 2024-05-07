package edu.hitsz.DAO;

import java.io.IOException;
import java.util.List;

public interface DAO {
    public List<Player> getAllScores() throws IOException;
    public List<Player> getScore(String username) throws IOException;
    public void addScore(Player player) throws IOException;
    public void updateScore(List<Player> players) throws IOException;
    public void deleteScore(int id);
}
