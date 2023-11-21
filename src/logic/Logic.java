package logic;

import javax.swing.JButton;
import javax.swing.JFrame;

import menu.MainMenu;

public class Logic {
    static String winner = null;

    private Logic() {throw new IllegalStateException("Utility class");}

    //Megvizsgálja, nyert-e valaki
    public static boolean checkForWin(JButton[][] board, int size) {
        return (checkForRow(board, size)||(checkForColumn(board, size))||checkForDiagonalLeftToRight(board, size)||checkForDiagonalRightToLeft(board, size));
    }

    //Megvizsgálja a sorokat, hogy nyertek-e
    public static boolean checkForRow(JButton[][] board, int size) {
        int consecutiveCount = (size == 3) ? 3 : 4;
    
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= size - consecutiveCount; j++) {
                if (consecutiveMatchesRow(board[i], j, consecutiveCount)) {
                    winner = board[i][j].getText();
                    return true;
                }
            }
        }
    
        return false;
    }
    //Segédfüggvény
    private static boolean consecutiveMatchesRow(JButton[] row, int startIndex, int consecutiveCount) {
        for (int k = 0; k < consecutiveCount - 1; k++) {
            if (!buttonmatch(row[startIndex + k], row[startIndex + k + 1])) {
                return false;
            }
        }
        return true;
    }

    //Megvizsgálja az oszlopokat, hogy nyertek-e
    public static boolean checkForColumn(JButton[][] board, int size) {
        int consecutiveCount = (size == 3) ? 3 : 4;
    
        for (int j = 0; j < size; j++) {
            for (int i = 0; i <= size - consecutiveCount; i++) {
                if (consecutiveMatchesColumn(board, i, j, consecutiveCount)) {
                    winner = board[i][j].getText();
                    return true;
                }
            }
        }
    
        return false;
    }
    
    private static boolean consecutiveMatchesColumn(JButton[][] board, int rowIndex, int columnIndex, int consecutiveCount) {
        for (int k = 0; k < consecutiveCount - 1; k++) {
            if (!buttonmatch(board[rowIndex + k][columnIndex], board[rowIndex + k + 1][columnIndex])) {
                return false;
            }
        }
        return true;
    }

    //Megvizsgálja az átlókat balról-jobbra, fentről-lefele, hogy nyertek-e
    public static boolean checkForDiagonalLeftToRight(JButton[][] board, int size){
        if(size==3){
            if(buttonmatch(board[0][0], board[1][1])&&buttonmatch(board[0][0], board[2][2])){
                winner = board[1][1].getText();
                return true;
            }
        }else{ //size > 3
            for(int i=0; i<size-3;i++){
                for(int j=0; j<size-3;j++){
                    if(buttonmatch(board[i][j], board[i+1][j+1])&&buttonmatch(board[i][j], board[i+2][j+2])&&buttonmatch(board[i][j], board[i+3][j+3])){
                        winner = board[i][j].getText();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Megvizsgálja az átlókat, jobbról-balra, fentről lefele, hogy nyertek-e
    public static boolean checkForDiagonalRightToLeft(JButton[][] board, int size){
        if(size==3){
            if(buttonmatch(board[0][2], board[1][1])&&buttonmatch(board[0][2], board[2][0])){
                winner = board[1][1].getText();
                return true;
            }
        }else{
            for(int i=0; i<size-3;i++){
                for(int j=3; j<size; j++){
                    if(buttonmatch(board[i][j], board[i+1][j-1])&&buttonmatch(board[i][j], board[i+2][j-2])&&buttonmatch(board[i][j], board[i+3][j-3])){
                        winner = board[i][j].getText();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Meegvizsgálja egyezik-e a két gomb
    public static boolean buttonmatch(JButton butt1, JButton butt2){
        if(butt1.getText().equals("")||butt2.getText().equals("")){
            return false;
        }else if(butt1.getText().equals(butt2.getText())){
            return true;
        }
        return false;
    }

    //Megvizsgálja, hogy tele van-e a tábla
    public static boolean isBoardFull(JButton[][] board, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getText().equals("")) {
                    winner = "TIE";
                    return false;
                }
            }
        }
        return true;
    }

    //Újraindítja a játékot
    public static void resetGame(JFrame frame) {
        frame.dispose();
        new MainMenu();
    }

    //Megnézi, hogy valami módon vége-e már a játéknak
    public static boolean isEndGame(JButton[][] board, int size){
        return checkForWin(board, size) || isBoardFull(board, size);
    }

    //Visszaadja a nyertest, vagy null értéket, ha még nincs
    public static String getWinner(JButton[][] board, int size){
        if(isEndGame(board,size))
            return winner;
        else return null;
    }
}
