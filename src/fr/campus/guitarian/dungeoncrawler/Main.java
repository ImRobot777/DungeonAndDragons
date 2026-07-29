package fr.campus.guitarian.dungeoncrawler;

import fr.campus.guitarian.dungeoncrawler.combat.CombatManager;
import fr.campus.guitarian.dungeoncrawler.core.Game;
import fr.campus.guitarian.dungeoncrawler.core.Menu;
import fr.campus.guitarian.dungeoncrawler.db.CharacterDAO;
import fr.campus.guitarian.dungeoncrawler.dice.Dice;
import fr.campus.guitarian.dungeoncrawler.dice.SixSidedDice;
import fr.campus.guitarian.dungeoncrawler.dice.TwentySidedDice;
import fr.campus.guitarian.dungeoncrawler.exceptions.OutOfBoardException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws SQLException        the sql exception
     * @throws IOException         the io exception
     * @throws OutOfBoardException the out of board exception
     */
    public static void main(String[] args) throws SQLException, IOException, OutOfBoardException {
        Menu menu = Menu.getInstance();
        int playerPosition = 1;
        int boardSize = 64;

        Random random = new Random();
        Dice sixSidedDice = new SixSidedDice(random);
        Dice twentySidedDice = new TwentySidedDice(random);

        // try-with-resources : characterDAO.close() is automaticaly called
        try (CharacterDAO characterDAO = new CharacterDAO()) {
            CombatManager combatManager = new CombatManager(sixSidedDice, twentySidedDice, menu);
            Game game = new Game(menu, playerPosition, boardSize, characterDAO, sixSidedDice, combatManager);
            game.start();
        }
    }
}