package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    public static void createMainMenu() {
        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel tttLabel = new JLabel("Tic-Tac-Toe");
        tttLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tttLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton startGameButton = new JButton("Start Game");
        JButton scoreboardButton = new JButton("Scoreboard");
        JButton exitButton = new JButton("Exit");

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); // Töröljük az összes tartalmat
                frame.repaint(); // Frissítjük az ablakot
                GameModesMenu menu = new GameModesMenu();
                menu.showGameModesMenu(frame); // Megjelenítjük az új menüt
            }
        });

        scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kezeljük a "Scoreboard" gomb eseményét
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Beállítjuk a gombok méretét
        Dimension buttonSize = new Dimension(150, 40);
        startGameButton.setPreferredSize(buttonSize);
        scoreboardButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        //BorderLayout elrendezés az ablakhoz
        frame.setLayout(new BorderLayout());
        frame.add(tttLabel, BorderLayout.NORTH);

        //JPanel a gombok középre igazításához
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Távolság a gombok között

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(startGameButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(scoreboardButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(exitButton, gbc);

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
