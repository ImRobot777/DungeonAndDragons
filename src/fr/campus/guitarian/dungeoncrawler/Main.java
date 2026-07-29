package fr.campus.guitarian.dungeoncrawler;

import fr.campus.guitarian.dungeoncrawler.combat.CombatManager;
import fr.campus.guitarian.dungeoncrawler.core.*;
import fr.campus.guitarian.dungeoncrawler.board.*;
import fr.campus.guitarian.dungeoncrawler.characters.*;
import fr.campus.guitarian.dungeoncrawler.characters.enemies.*;
import fr.campus.guitarian.dungeoncrawler.characters.types.*;
import fr.campus.guitarian.dungeoncrawler.db.*;
import fr.campus.guitarian.dungeoncrawler.dice.Dice;
import fr.campus.guitarian.dungeoncrawler.dice.SixSidedDice;
import fr.campus.guitarian.dungeoncrawler.dice.TwentySidedDice;
import fr.campus.guitarian.dungeoncrawler.exceptions.OutOfBoardException;
import fr.campus.guitarian.dungeoncrawler.items.defensive.*;
import fr.campus.guitarian.dungeoncrawler.items.offensive.*;

import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws SQLException, RuntimeException, IOException, OutOfBoardException {
        Menu menu = Menu.getInstance();
        int playerPosition = 1;
        int boardSize = 64;
        CharacterDAO characterDAO = new CharacterDAO() ;

        Random random = new Random();
        Dice sixSidedDice = new SixSidedDice(random);
        Dice twentySidedDice = new TwentySidedDice(random);
        CombatManager combatManager = new CombatManager(sixSidedDice, twentySidedDice, menu);

        Game game = new Game(menu, playerPosition, boardSize, characterDAO, sixSidedDice, combatManager);
        game.start();
    }
}


