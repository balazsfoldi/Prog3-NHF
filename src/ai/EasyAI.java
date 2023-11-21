package ai;

import java.util.Random;
import javax.swing.JButton;

import logic.Logic;

public class EasyAI {
    private EasyAI() {throw new IllegalStateException("Utility class");}
    private static Random random = new Random();

    public static void makeRandomMove(JButton[][] board, int size) {
        int row;
        int col;
        if (Logic.isBoardFull(board, size)) {
           return;
         }
         //Keres egy random helyet ahova lerakja a jelölőt
        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while (!board[row][col].getText().equals(""));
        board[row][col].setText("O");
    }
}
