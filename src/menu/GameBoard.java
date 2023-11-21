package menu;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.io.*;

import logic.Logic;
import ai.*;
import scoremanagement.ScoreManager;

public class GameBoard extends JPanel{
    //Tagfüggvények létrehozása, a copy constructor miatt
    JButton[][] board;
    int currentPlayer;
    JLabel currentPlayerLabel;
    int size;
    JFrame frame;
    String player1;
    String player2;
    int difficulty;
    public GameBoard(JFrame f, String p1, String p2, int n, int diff) {
        //Tagfüggvények beállítása
        frame = f;
        size = n;
        board = new JButton[size][size];
        currentPlayer = 1;
        player1 = p1;
        player2 = p2;
        difficulty= diff;

        //Frame beállítása
        frame.setTitle("Tic Tac Toe");
        frame.setSize(size*100, size*100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        frame.add(topPanel, BorderLayout.NORTH);
        currentPlayerLabel = new JLabel(player1);
        topPanel.add(currentPlayerLabel);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(size, size));
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                save(); // Játék mentése a kilépéskor
            }
        });

        //Létrehozunk size*size méretű JButton mátrixot
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new JButton("");
                board[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                board[i][j].setFocusPainted(false);
                board[i][j].setBackground(Color.white);
                addButtonActionListener(board[i][j]); //Minden JButton-re hozzáadjuk az actionLister-t
                gamePanel.add(board[i][j]);
            }
        }

        currentPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        frame.setVisible(true);
    }

    public GameBoard(GameBoard b){ //Copy Constructor
        this.board=b.board;
        this.currentPlayer=b.currentPlayer;
        this.currentPlayerLabel=b.currentPlayerLabel;
        this.size=b.size;
        this.frame=b.frame;
        this.player1 = b.player1;
        this.player2 = b.player2;
        this.difficulty = b.difficulty;
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                save(); // Játék mentése a kilépéskor
            }
        });
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                addButtonActionListener(board[i][j]);
            }
        }
        frame.setVisible(true);
    }

    //Játék mentése függvény
    private void save() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("save.txt"))) {
            output.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void addButtonActionListener(JButton button) {
        button.addActionListener(e -> handleButtonClick((JButton) e.getSource()));
    }

    //Kezeli a gombnoymást
    private void handleButtonClick(JButton clickedButton) {
        if (clickedButton.getText().equals("")) {
            if (currentPlayer == 1) {
                handlePlayerMove(clickedButton, "X", player2, 2);
            } else if (difficulty == 0) {
                handlePlayerMove(clickedButton, "O", player1, 1);
            }
        }
    }
    
    //Játékos köre
    private void handlePlayerMove(JButton button, String symbol, String nextPlayer, int nextPlayerIndex) {
        button.setText(symbol);
        if(checkEnd()) return;
        currentPlayerLabel.setText(nextPlayer);
        currentPlayer = nextPlayerIndex;
        if(nextPlayer.equals("AI")){
            handleAIMove();
            if(checkEnd()) return;
            currentPlayerLabel.setText(player1 + "'s turn");
            currentPlayer = 1;
        }
    }
    
    //AI köre
    private void handleAIMove() {
        if (difficulty == 1) {
            EasyAI.makeRandomMove(board, size);
        } else if (difficulty == 2) {
            HardAI.makeAIMove(board, size);
        }
    }

    //Megvizsgálja hogy vége-e a játéknak
    private boolean checkEnd(){
        boolean result = false;
        if (Logic.checkForWin(board, size)) {
            showEndGameMessage();
            Logic.resetGame(frame);
            result = true;
        } else if (Logic.isBoardFull(board, size)) {
            JOptionPane.showMessageDialog(frame, "It's a draw!");
            if (!player2.equals("AI")) {
                ScoreManager.entryScore(player1, player2, 0, "scores.csv");
            }
            Logic.resetGame(frame);
            result = true;
        }
        return result;
    }
    
    //Kiírja a játék eredményét, ha nem döntetlen
    private void showEndGameMessage() {
        String winner = currentPlayer == 1 ? player1 : player2;
        int winnerid = currentPlayer ==1 ? 1 : 2;
        if(!player2.equals("AI"))
            ScoreManager.entryScore(player1, player2, winnerid, "scores.csv");
        JOptionPane.showMessageDialog(frame, winner + " wins!");
    }
}