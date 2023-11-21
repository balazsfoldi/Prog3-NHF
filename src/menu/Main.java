package menu;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static File file = new File("save.txt");

    public static void main(String[] args) {
        // Ellenőrizzük, hogy van-e már mentett játék
        if (savedFile(file)) {
            // Ha van, akkor betöltjük
            GameBoard game = loadGame(file);

            // Töröljük a fájlt
            if (deleteFile(file.toPath())) {
                // A betöltött játékot adjuk hozzá a JFrame-hez
                new GameBoard(game);
            } else {
                System.err.println("Hiba a mentett játék törlése során.");
            }
        } else {
            // Ha nincs mentett játék, akkor új játékot hozunk létre
            new MainMenu();
        }
    }

    // Fájl törlésének elvégzése, ellenőrzése
    private static boolean deleteFile(Path path) {
        try {
            Files.delete(path);
            System.out.println("A mentett játék sikeresen törölve.");
            return true;
        } catch (Exception e) {
            System.err.println("Hiba a mentett játék törlése során: " + e.getMessage());
            return false;
        }
    }

    //Függvény mentett játék létezésének meghatározására
    private static boolean savedFile(File file) {
        return (file.exists() && !file.isDirectory());
    }

    //Játék betöltése
    public static GameBoard loadGame(File file) {
        GameBoard game = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            game = (GameBoard) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }
}