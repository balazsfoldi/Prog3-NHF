package logic;

import java.util.Random;

import javax.swing.JButton;

import org.junit.*;

public class LogicTest {
    Random random = new Random();

    @Test
    public void testGetWinner() {
        JButton[][] board = new JButton[3][3]; 
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = new JButton("O");
            }
        }
        Assert.assertEquals("O", Logic.getWinner(board, 3));
    }

    @Test
    public void testIsBoardFull() {
        JButton[][] board = new JButton[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = new JButton("O");
            }
        }
        Assert.assertTrue(Logic.isBoardFull(board, 3));
    }

    @Test
    public void testIsEndGame() {
        JButton[][] board = new JButton[3][3]; 
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = new JButton("X");
            }
        }
        Assert.assertTrue(Logic.isEndGame(board, 3));
    }

    @Test
    public void testButtonmatch() {
        JButton bigRedButton = new JButton("Do not press!");
        JButton bigBlueButton = new JButton("Press!");
        Assert.assertFalse(Logic.buttonmatch(bigRedButton, bigBlueButton));
    }

    @Test
    public void testCheckForRow() {
        int randomRow = random.nextInt(7);
        int randomCol = random.nextInt(4);
        JButton[][] board = new JButton[7][7]; 
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                board[i][j] = new JButton("");
            }
        }
        for(int j = randomCol; j < 7; j++){
            board[randomRow][j].setText("X");
        }
        Assert.assertTrue(Logic.checkForRow(board, 7));
    }
    
    @Test
    public void testCheckForColumn() {
        int randomRow = random.nextInt(4);
        int randomCol = random.nextInt(7);
        JButton[][] board = new JButton[7][7]; 
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                board[i][j] = new JButton("");
            }
        }
        for(int i = randomRow; i < 7; i++){
            board[i][randomCol].setText("X");
        }
        Assert.assertTrue(Logic.checkForColumn(board, 7));

    }

    @Test
    public void testCheckForDiagonalLeftToRight() {
        int randomRow = random.nextInt(4);
        int randomCol = random.nextInt(4);
        JButton[][] board = new JButton[7][7]; 
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                board[i][j] = new JButton("");
            }
        }
        for(int i = 0; i<4; i++){
            board[randomRow+i][randomCol+i].setText("X");
        }
        Assert.assertTrue(Logic.checkForDiagonalLeftToRight(board,7));
    }

    @Test
    public void testCheckForDiagonalRightToLeft() {
        int randomRow = random.nextInt(4);
        int randomCol = random.nextInt(4)+3;
        JButton[][] board = new JButton[7][7]; 
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                board[i][j] = new JButton("");
            }
        }
        for(int i = 0; i<4; i++){
            board[randomRow+i][randomCol-i].setText("X");
        }
        Assert.assertTrue(Logic.checkForDiagonalRightToLeft(board,7));
    }
}
