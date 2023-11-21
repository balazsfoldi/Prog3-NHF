package scoremanagement;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class ScoreManagerTest {

        @Test
    public void testEntryScore() {
        String player1 = "Alice";
        String player2 = "Bob";
        int winner = 1;

        ScoreManager.entryScore(player1, player2, winner, "test.csv");
        ScoreManager.entryScore(player1, player2, winner, "test.csv");

        List<PlayerScore> playerScores = ScoreManager.readScoresFromFile("test.csv");
        
        // Assuming Alice won against Bob, so Alice should have a score of 2, and Bob should have a score of 0.
        PlayerScore aliceScore = ScoreManager.findPlayerScore(playerScores, "Alice");
        PlayerScore bobScore = ScoreManager.findPlayerScore(playerScores, "Bob");

        assertEquals(4, aliceScore.getScore());
        assertEquals(0, bobScore.getScore());

        File file = new File("test.csv");
        file.deleteOnExit();
    }
}
