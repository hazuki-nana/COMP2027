package edu.hitsz.DAO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements DAO{

    private InputStream in;
    private String folderPath = "DATABASE";
    private String fileName = "SCORE.txt";
    private BufferedReader br;
    private String folderFilePath = folderPath + File.separator + fileName;

    public DaoImpl() throws IOException {
        File folder = new File(folderPath);
        File file = new File(folderFilePath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        if(!file.exists()){
            file.createNewFile();
        }
        try{
            in = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(in));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Player> getAllScores() throws IOException {
        List<Player> players = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(folderFilePath));
        for (String line : lines) {
            String[] split = line.split("  ");
            Player sc = new Player(split[0], Integer.parseInt(split[1]), split[2]);
            players.add(sc);
        }
        return players;
    }

    @Override
    public List<Player> getScore(String username) throws IOException {
        List<Player> res = new ArrayList<>();
        for (Player User : this.getAllScores()){
            if(User.username.equals(username))
                res.add(User);
        }
        return res;
    }

    @Override
    public void addScore(Player score) throws IOException {
        Files.writeString(Paths.get(folderFilePath), "\n"+score.toString(), StandardOpenOption.APPEND);
    }

    @Override
    public void updateScore(Player score) {

    }

    @Override
    public void deleteScore(int id) {

    }
}
