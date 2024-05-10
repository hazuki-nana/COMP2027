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
    private String fileName = "SCORE";
    private BufferedReader br;
    private String folderFilePath;

    public DaoImpl(String diff) throws IOException {
        this.fileName = fileName + diff + ".txt";
        folderFilePath = folderPath + File.separator + fileName;
        File folder = new File(folderPath);
        File file = new File(folderFilePath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        if(!file.exists()){
            file.createNewFile();
        }
//        try{
//            in = new FileInputStream(file);
//            br = new BufferedReader(new InputStreamReader(in));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
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
            if(User.getUsername().equals(username))
                res.add(User);
        }
        return res;
    }

    @Override
    public void addScore(Player player) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(folderFilePath), StandardOpenOption.APPEND);
        writer.write(player.toString());
        writer.newLine();
        writer.close();
//        Files.writeString(Paths.get(folderFilePath), "\n"+player.toString(), StandardOpenOption.APPEND);
    }

    @Override
    public void updateScore(List<Player> players) throws IOException {
//        Files.writeString(Paths.get(folderFilePath+"123.txt"), players.get(0).toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
//        System.out.println("\n"+players.get(0).toString());
//        for(int i = 1; i < players.size(); i++){
//            Files.writeString(Paths.get(folderFilePath+"123.txt"), "\n"+ players.get(i).toString(), StandardOpenOption.APPEND);
//        }
//
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(folderFilePath));
        for (Player player : players){
            writer.write(player.toString());
            writer.newLine();
        }
        writer.close();
    }

    @Override
    public void deleteScore(int id) {

    }
}
