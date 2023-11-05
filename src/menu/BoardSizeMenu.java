package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardSizeMenu {
    public static void showBoardSizeMenu(JFrame frame, String player1, String player2){
        frame.setTitle("Tic-Tac-Toe: " +player1 +" VS. "+player2);
        JLabel titleLabel = new JLabel("Choose the size of the board!");
        titleLabel.setFont(new Font("Ariel", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JRadioButton three = new JRadioButton("3X3");
        three.setSelected(true);
        JRadioButton five = new JRadioButton("5X5");
        JRadioButton seven = new JRadioButton("7X7");
        ButtonGroup group = new ButtonGroup();
        group.add(three);
        group.add(five);
        group.add(seven);

        JButton startButton = new JButton("Start Game");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); // Töröljük az összes tartalmat
                frame.repaint(); // Frissítjük az ablakot
                TTTBoard board = new TTTBoard();
                if(five.isSelected()){
                    board.createGameBoard(frame, player1, player2, 5); // Megjelenítjük az új menüt
                }else if(seven.isSelected()){
                    board.createGameBoard(frame, player1, player2, 7); 
                }else  board.createGameBoard(frame, player1, player2, 3); 
            }
        });

        JPanel panel = new JPanel(new GridLayout(3,1));
        panel.add(three);
        panel.add(five);
        panel.add(seven);

        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);
        frame.setVisible(true);

    }
}
