package menu;

import javax.swing.*;
import java.awt.*;

import scoremanagement.*;

public class MainMenu {
    JFrame frame;
    
    public MainMenu() {
        //JFrame beállítása
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel tttLabel = new JLabel("Tic-Tac-Toe");
        tttLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tttLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton startGameButton = new JButton("Start Game");
        JButton scoreboardButton = new JButton("Scoreboard");
        JButton exitButton = new JButton("Exit");

        //ActionListener-ek beállítása
        startGameButton.addActionListener(e -> new GameModes(frame));

        scoreboardButton.addActionListener(e -> new Scoreboard(frame));

        exitButton.addActionListener(e -> System.exit(0));

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
