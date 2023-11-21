package scoremanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import menu.MainMenu;

public class Scoreboard {
    private JTable scoreboardTable;

    public Scoreboard(JFrame frame) {
        //Frame beállítása
        frame.getContentPane().removeAll(); // Töröljük az összes tartalmat
        frame.repaint(); // Frissítjük az ablakot
        
        frame.setTitle("Scoreboard");

        // Táblázat fejléc
        String[] columnNames = {"Name", "Score"};

        // Adatok betöltése a scores.csv fájlból
        Object[][] data = loadDataFromCSV("scores.csv");

        // Táblázat modell létrehozása
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            // Felülírjuk a isCellEditable metódust, hogy a táblázatot ne lehessen szerkeszteni
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // Táblázat inicializálása a modell segítségével
        scoreboardTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(scoreboardTable);

        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(new JLabel("Scoreboard", SwingConstants.CENTER), gbc);

        gbc.gridy = 1;
        gbc.weighty = 5;
        frame.add(scrollPane, gbc);

        // Back gomb 
        JButton backButton = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        frame.add(backButton, gbc);

        backButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //Adatok betöltése 
    private Object[][] loadDataFromCSV(String filePath) {
        // Táblázat adatainak betöltése a CSV fájlból
        Object[][] data = new Object[0][];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    Object[] row = new Object[]{parts[0], parts[1]};
                    data = appendRow(data, row);
                } else {
                    System.err.println("Invalid data format in CSV file: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private Object[][] appendRow(Object[][] data, Object[] row) {
        // Új sor hozzáadása a táblázathoz
        Object[][] newData = new Object[data.length + 1][];
        System.arraycopy(data, 0, newData, 0, data.length);
        newData[data.length] = row;
        return newData;
    }
}