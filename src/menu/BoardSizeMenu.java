package menu;

import javax.swing.*;
import java.awt.*;

public class BoardSizeMenu {
    public BoardSizeMenu(JFrame frame, String player1, String player2, int difficulty){
        //Frame beállítása
        frame.getContentPane().removeAll(); // Töröljük az összes tartalmat
        frame.repaint(); // Frissítjük az ablakot
        frame.setTitle("Tic-Tac-Toe: " +player1 +" VS. "+player2);
        JLabel titleLabel = new JLabel("Choose the size of the board!");
        titleLabel.setFont(new Font("Ariel", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //Méret opciók
        JRadioButton three = new JRadioButton("3X3");
        three.setSelected(true);
        JRadioButton five = new JRadioButton("5X5");
        JRadioButton seven = new JRadioButton("7X7");
        ButtonGroup group = new ButtonGroup();
        group.add(three);
        group.add(five);
        group.add(seven);

        JButton startButton = new JButton("Start Game");

        startButton.addActionListener(e -> {
            frame.getContentPane().removeAll(); // Töröljük az összes tartalmat
            frame.repaint(); // Frissítjük az ablakot
        
            int boardSize = 3; // Default board size
            if (five.isSelected()) {
                boardSize = 5;
            } else if (seven.isSelected()) {
                boardSize = 7;
            }
        
            new GameBoard(frame, player1, player2, boardSize, difficulty); // Megjelenítjük az új menüt
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
