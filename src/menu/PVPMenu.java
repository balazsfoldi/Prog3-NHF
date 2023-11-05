package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PVPMenu {
    public static void showPlayerVsPlayerMenu(JFrame frame) {
        JLabel titleLabel = new JLabel("Player VS. Player");
        titleLabel.setFont(new Font("Arial",Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel playerNamesPanel = new JPanel(new GridLayout(2, 2));

        JTextField player1TextField = new JTextField("Enter your name");
        JTextField player2TextField = new JTextField("Enter your name");

        playerNamesPanel.add(new JLabel("Player X:"));
        playerNamesPanel.add(player1TextField);
        playerNamesPanel.add(new JLabel("Player O:"));
        playerNamesPanel.add(player2TextField);

        JButton startGameButton = new JButton("Next");

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); // Töröljük az összes tartalmat
                frame.repaint(); // Frissítjük az ablakot
                BoardSizeMenu menu = new BoardSizeMenu();
                menu.showBoardSizeMenu(frame, player1TextField.getText(), player2TextField.getText()); // Megjelenítjük az új menüt
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(playerNamesPanel, BorderLayout.CENTER);
        frame.add(startGameButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
