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
    public static void createGameBoard(JFrame frame, String player1, String player2, int n) {
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
                                    makeRandomMove();
                                    currentPlayerLabel.setText(player1+"'s turn");
                                    currentPlayer = 1;
                                }
                            }else{
                                clickedButton.setText("O");
                                currentPlayerLabel.setText(player1);
                                currentPlayer = 1;
                            }
                        }

                        if (checkForWin()) {
                            JOptionPane.showMessageDialog(frame, "Player " + (currentPlayer == 1 ? "1" : "2") + " wins!");
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
        // Implement your win condition checking logic here
        // Return true if there is a win, false otherwise
        return false;
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