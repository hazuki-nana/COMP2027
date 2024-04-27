package edu.hitsz.DAO;

public class Player implements Comparable{
    private int score;
    private String username;
    private String time;

    public Player(String username, int score, String time) {
        this.username = username;
        this.score = score;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {}
    public String toString()
    {
        return username + "  " + score + "  " + time;
    }

    @Override
    public int compareTo(Object o) {
        Player s = (Player)o;
        return s.score - this.score;
    }
}

