package menu;

import javax.swing.*;
import java.awt.*;

public class PVPMenu {

    public PVPMenu(JFrame frame) {
        //Frame beállítása
        frame.getContentPane().removeAll(); // Töröljük az összes tartalmat
        frame.repaint(); // Frissítjük az ablakot
        JLabel titleLabel = new JLabel("Player VS. Player");
        titleLabel.setFont(new Font("Arial",Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel playerNamesPanel = new JPanel(new GridLayout(2, 2));

        //Nevek
        JTextField player1TextField = new JTextField("Enter your name");
        JTextField player2TextField = new JTextField("Enter your name");

        playerNamesPanel.add(new JLabel("Player X:"));
        playerNamesPanel.add(player1TextField);
        playerNamesPanel.add(new JLabel("Player O:"));
        playerNamesPanel.add(player2TextField);

        JButton nextButton = new JButton("Next");

        nextButton.addActionListener(e -> {
            //Ellenőrzések a kikötésekre
            if(player1TextField.getText().equalsIgnoreCase(player2TextField.getText())){
                titleLabel.setText("Names must be different!");
                return;
            }
            if(player1TextField.getText().equalsIgnoreCase("AI")||player2TextField.getText().equalsIgnoreCase("AI")){
                titleLabel.setText("Your name cannot be 'AI'!");
                return;
            }
            if(player1TextField.getText().trim().equals("")||player2TextField.getText().trim().equals("")){
                titleLabel.setText("Your name cannot be blank!");
                return;
            }
            new BoardSizeMenu(frame, player1TextField.getText(), player2TextField.getText(), 0);}
        );

        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(playerNamesPanel, BorderLayout.CENTER);
        frame.add(nextButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
