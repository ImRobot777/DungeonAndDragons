package fr.campus.guitarian.dungeoncrawler;

import fr.campus.guitarian.dungeoncrawler.core.Game;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, RuntimeException, IOException {
        Game game = new Game();
        game.start();
    }
}