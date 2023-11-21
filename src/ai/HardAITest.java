package ai;

import java.util.Random;

import javax.swing.JButton;

import org.junit.*;

public class HardAITest {
    @Test
    public void testMakeAIMove() {
        Random random = new Random();
        int randomRow = random.nextInt(7);
        int randomCol = random.nextInt(4);

        JButton[][] actualBoard = new JButton[7][7]; 
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                actualBoard[i][j] = new JButton("");
            }
        }
        for(int j = randomCol; j < 3; j++){
            actualBoard[randomRow][j].setText("X");
        }
        ai.HardAI.makeAIMove(actualBoard, 7);

        JButton[][] expectedBoard = actualBoard.clone();
        expectedBoard[randomRow][randomCol+3].setText("O");

        Assert.assertEquals(expectedBoard, actualBoard);



    }
}
