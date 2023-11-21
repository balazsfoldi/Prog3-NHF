package menu;

import javax.swing.*;
import java.awt.*;

class GameModes{
    public GameModes(JFrame frame) {
        //Frame beállítása
        frame.getContentPane().removeAll(); // Töröljük az összes tartalmat
        frame.repaint(); // Frissítjük az ablakot
        JLabel titleLabel = new JLabel("Game Modes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton playerVsPlayerButton = new JButton("Player vs. Player");
        JButton playerVsAIButton = new JButton("Player vs. AI");

        playerVsPlayerButton.setPreferredSize(new Dimension(150, 40));
        playerVsAIButton.setPreferredSize(new Dimension(150, 40));

        //ActionListener beállítások
        playerVsPlayerButton.addActionListener(e -> new PVPMenu(frame));

        playerVsAIButton.addActionListener(e -> new PVEMenu(frame));

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(playerVsPlayerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(playerVsAIButton, gbc);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.revalidate(); // Újravalidáljuk az ablakot a frissítések alkalmazásához
    }
}