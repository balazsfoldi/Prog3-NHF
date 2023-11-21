package scoremanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreManager {
    private ScoreManager() {throw new IllegalStateException("Utility class");}

    //Új rekord felvétele File nevű fájlba. A winner mutatja ki nyert. 
    //Ha 1, akkor player1, ha 2 akkor player2, egyéb esetben döntetlen
    public static void entryScore(String player1, String player2, int winner, String File) {
        List<PlayerScore> playerScores = readScoresFromFile(File);
        updateScores(playerScores, player1, player2, winner);
        saveScoresToFile(playerScores, File);
    }

    //Rekordokat olvas ki fájlból
    public static List<PlayerScore> readScoresFromFile(String File) {
        List<PlayerScore> playerScores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(File))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String playerName = parts[0];
                int score = Integer.parseInt(parts[1]);
                playerScores.add(new PlayerScore(playerName, score));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return playerScores;
    }

    //Frissíti a rekordokat a listába
    private static void updateScores(List<PlayerScore> playerScores, String player1, String player2, int winner) {
        PlayerScore winnerScore = findPlayerScore(playerScores, (winner == 1) ? player1 : player2);
        PlayerScore loserScore = findPlayerScore(playerScores, (winner == 1) ? player2 : player1);

        if (winnerScore != null) {
            winnerScore.setScore(winnerScore.getScore() + 2);
        } else {
            playerScores.add(new PlayerScore((winner == 1) ? player1 : player2, 2));
        }

        if (loserScore == null) {
            playerScores.add(new PlayerScore((winner == 1) ? player2 : player1, 0));
        }

        if (winner == 0) {
            if (findPlayerScore(playerScores, player1) == null) {
                playerScores.add(new PlayerScore(player1, 1));
            }

            if (findPlayerScore(playerScores, player2) == null) {
                playerScores.add(new PlayerScore(player2, 1));
            }
        }

        Collections.sort(playerScores, Comparator.comparing(PlayerScore::getScore).reversed().thenComparing(PlayerScore::getName));
    }

    //Menti a rekordokat fájlba
    private static void saveScoresToFile(List<PlayerScore> playerScores, String File) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(File))) {
            for (PlayerScore playerScore : playerScores) {
                writer.write(playerScore.getName() + ";" + playerScore.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Játékoshoz tartozó pontot ad vissza
    public static PlayerScore findPlayerScore(List<PlayerScore> playerScores, String playerName) {
        for (PlayerScore playerScore : playerScores) {
            if (playerScore.getName().equals(playerName)) {
                return playerScore;
            }
        }
        return null;
    }
}

//Ez az osztály tárolja együtt az adatokat
class PlayerScore {
    private String name;
    private int score;

    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}