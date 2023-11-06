package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TTTBoard {
    static JButton[][] board;
    static int currentPlayer;
    static JLabel currentPlayerLabel;
    static int size;
    public static void createGameBoard(JFrame frame, String player1, String player2, int n, int difficulty) {
        size = n;
        board = new JButton[size][size];
        currentPlayer = 1;

        frame.setTitle("Tic Tac Toe");
        frame.setSize(size*100, size*100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        frame.add(topPanel, BorderLayout.NORTH);
        currentPlayerLabel = new JLabel(player1);
        topPanel.add(currentPlayerLabel);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(size, size));
        frame.add(gamePanel, BorderLayout.CENTER);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new JButton("");
                board[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                board[i][j].setFocusPainted(false);
                board[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();

                        if (clickedButton.getText().equals("")) {
                            if (currentPlayer == 1) {
                                clickedButton.setText("X");
                                currentPlayerLabel.setText(player2);
                                currentPlayer = 2;
                                if(player2.equals("AI")){
                                    if (checkForWin()) {
                                        JOptionPane.showMessageDialog(frame, (currentPlayer == 1 ? player2 : player1) + " wins!");
                                        frame.dispose();
                                        resetGame();
                                    } else if (isBoardFull()) {
                                        JOptionPane.showMessageDialog(frame, "It's a draw!");
                                        frame.dispose();
                                        resetGame();
                                    }
                                    if(difficulty==1){
                                        makeRandomMove();
                                        currentPlayerLabel.setText(player1+"'s turn");
                                        currentPlayer = 1;
                                    }else if(player2.equals("AI")&&difficulty==2){
                                        //TODO;
                                    }
                                    return;
                                }
                            }else if(difficulty==0){
                                clickedButton.setText("O");
                                currentPlayerLabel.setText(player1);
                                currentPlayer = 1;
                            }
                        }
                        if (checkForWin()) {
                            JOptionPane.showMessageDialog(frame, (currentPlayer == 1 ? player2 : player1) + " wins!");
                            frame.dispose();
                            resetGame();
                        } else if (isBoardFull()) {
                            JOptionPane.showMessageDialog(frame, "It's a draw!");
                            frame.dispose();
                            resetGame();
                        }
                    }
                });
                gamePanel.add(board[i][j]);
            }
        }

        currentPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        frame.setVisible(true);
    }

    private static boolean checkForWin() {
        return (checkForRow(size)||(checkForColumn(size))||checkForDiagonalLeftToRight(size)||checkForDiagonalRightToLeft(size));
    }

    private static boolean checkForRow(int size){
        if(size==3){
            for(int i=0; i<size; i++){
                if(buttonmatch(board[i][0], board[i][1])&&buttonmatch(board[i][0], board[i][2]))
                    return true;
            }
        }else{
            for(int i=0; i<size;i++){
                for(int j=0; j<size-3;j++){
                    if(buttonmatch(board[i][j], board[i][j+1])&&buttonmatch(board[i][j], board[i][j+2])&&buttonmatch(board[i][j], board[i][j+3]))
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean checkForColumn(int size){
        if(size==3){
            for(int j=0; j<size; j++){
                if(buttonmatch(board[0][j], board[1][j])&&buttonmatch(board[0][j], board[2][j]))
                    return true;
            }
        }else{
            for(int j=0; j<size;j++){
                for(int i=0; i<size-3;i++){
                    if(buttonmatch(board[i][j], board[i+1][j])&&buttonmatch(board[i][j], board[i+2][j])&&buttonmatch(board[i][j], board[i+3][j]))
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean checkForDiagonalLeftToRight(int size){
        if(size==3){
            if(buttonmatch(board[0][0], board[1][1])&&buttonmatch(board[0][0], board[2][2]))
                return true;
        }else{
            for(int i=0; i<size-3;i++){
                for(int j=0; j<size-3;j++){
                    if(buttonmatch(board[i][j], board[i+1][j+1])&&buttonmatch(board[i][j], board[i+2][j+2])&&buttonmatch(board[i][j], board[i+3][j+3]))
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean checkForDiagonalRightToLeft(int size){
        if(size==3){
            if(buttonmatch(board[0][2], board[1][1])&&buttonmatch(board[0][2], board[2][0]))
                return true;
        }else{
            for(int i=0; i<size-3;i++){
                for(int j=3; j<size; j++){
                    if(buttonmatch(board[i][j], board[i+1][j-1])&&buttonmatch(board[i][j], board[i+2][j-2])&&buttonmatch(board[i][j], board[i+3][j-3]))
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean buttonmatch(JButton butt1, JButton butt2){
        if(butt1.getText().equals("")||butt2.getText().equals("")){
            return false;
        }else if(butt1.getText().equals(butt2.getText())){
            return true;
        }else return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void resetGame() {
        MainMenu mainmenu = new MainMenu();
        mainmenu.createMainMenu();
    }

    private static void makeRandomMove() {
        Random random = new Random();
        int row, col;
        if(isBoardFull())
            return;
        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while (!board[row][col].getText().equals(""));
        board[row][col].setText("O");
    }
}