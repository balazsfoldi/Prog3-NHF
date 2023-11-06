package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PVEMenu {
    public static void showPlayerVsAIPanel(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(2,1));

        JLabel titleLabel = new JLabel("Player VS. AI");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JRadioButton diff1 = new JRadioButton("Difficulty 1");
        diff1.setSelected(true);
        JRadioButton diff2 = new JRadioButton("Difficulty 2");
        ButtonGroup group = new ButtonGroup();
        group.add(diff1);
        group.add(diff2);
        JButton nextButton = new JButton("Next");

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int difficulty = 1;
                if(diff2.isSelected()) difficulty=2;
                frame.getContentPane().removeAll(); // Töröljük az összes tartalmat
                frame.repaint(); // Frissítjük az ablakot
                BoardSizeMenu menu = new BoardSizeMenu();
                menu.showBoardSizeMenu(frame, "Player", "AI", difficulty); // Megjelenítjük az új menüt
            }
        });

        panel.add(diff1);
        panel.add(diff2);

        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(nextButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
