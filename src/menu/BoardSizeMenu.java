package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardSizeMenu {
    public static void showBoardSizeMenu(JFrame frame){
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
                // Kezeljük a "Next" gomb eseményét
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
