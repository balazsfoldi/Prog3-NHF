package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameModesMenu {
    public static void showGameModesMenu(JFrame frame) {
        JLabel titleLabel = new JLabel("Game Modes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton playerVsPlayerButton = new JButton("Player vs. Player");
        JButton playerVsAIButton = new JButton("Player vs. AI");

        playerVsPlayerButton.setPreferredSize(new Dimension(150, 40));
        playerVsAIButton.setPreferredSize(new Dimension(150, 40));

        playerVsPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); // Töröljük az összes tartalmat
                frame.repaint(); // Frissítjük az ablakot
                PVPMenu menu = new PVPMenu();
                menu.showPlayerVsPlayerMenu(frame); // Megjelenítjük az új menüt
            }
        });

        playerVsAIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); // Töröljük az összes tartalmat
                frame.repaint(); // Frissítjük az ablakot
                PVEMenu menu = new PVEMenu();
                menu.showPlayerVsAIPanel(frame); // Megjelenítjük az új menüt
            }
        });

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

        frame.revalidate(); // Újra validáljuk az ablakot a frissítések alkalmazásához
    }
}