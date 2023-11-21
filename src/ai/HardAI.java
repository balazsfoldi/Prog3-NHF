package ai;

import javax.swing.JButton;
import logic.*;     

public class HardAI {
    private static int maxDepth;
    private HardAI() {throw new IllegalStateException("Utility class");}

    public static void makeAIMove(JButton[][] board, int size) {
        //Maximális mélységek beállítása heurisztika alapján
        if(size == 3) maxDepth = Integer.MAX_VALUE; 
        if(size == 5) maxDepth = 5;
        if(size == 7) maxDepth = 4;  
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
    
        //Algoritmus implementálása (Részletesen le van írva dokumentációban)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getText().equals("")) {
                    board[i][j].setText("O");
                    int score = minimax(board, size, 0, false, alpha, beta, maxDepth);
                    board[i][j].setText("");
    
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
    
        board[bestMove[0]][bestMove[1]].setText("O");
    }
    
    //Kiértékelő függvény
    private static int getScore(String winner) {
        if (winner.equals("X")) return -1;
        if (winner.equals("O")) return 1;
        if (winner.equals("TIE")) return 0;
        return 0;
    }
    
    private static int minimax(JButton[][] board, int size, int depth, boolean isMaximizing, int alpha, int beta, int maxDepth) {
        // Alapeset: elérte a maximális mélységet
        if (depth == maxDepth) {
            return 0;
        }
    
        // Nyertes keresése a jelenlegi állapotban
        String winner = Logic.getWinner(board, size);
        if (winner != null) {
            return getScore(winner);
        }
    
        // Minimax algoritmus végrehajtása
        if (isMaximizing) {
            return maximize(board, size, depth, alpha, beta, maxDepth);
        } else {
            return minimize(board, size, depth, alpha, beta, maxDepth);
        }
    }
    
    private static int maximize(JButton[][] board, int size, int depth, int alpha, int beta, int maxDepth) {
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getText().equals("")) {
                    board[i][j].setText("O");
                    int score = minimax(board, size, depth + 1, false, alpha, beta, maxDepth);
                    board[i][j].setText("");
                    bestScore = Math.max(score, bestScore);
                    alpha = Math.max(alpha, bestScore);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
        }
        return bestScore;
    }
    
    private static int minimize(JButton[][] board, int size, int depth, int alpha, int beta, int maxDepth) {
        int bestScore = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getText().equals("")) {
                    board[i][j].setText("X");
                    int score = minimax(board, size, depth + 1, true, alpha, beta, maxDepth);
                    board[i][j].setText("");
                    bestScore = Math.min(score, bestScore);
                    beta = Math.min(beta, bestScore);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
        }
        return bestScore;
    }
}