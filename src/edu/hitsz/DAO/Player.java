package edu.hitsz.DAO;

public class Player implements Comparable{
    int score;
    String username;
    String time;

    public Player(String username, int score, String time) {
        this.username = username;
        this.score = score;
        this.time = time;
    }

    public String toString()
    {
        return username + "  " + score + "  " + time;
    }

    @Override
    public int compareTo(Object o) {
        Player s = (Player)o;
        return this.score - s.score;
    }
}

