package fr.campus.guitarian.dungeoncrawler.core;

import fr.campus.guitarian.dungeoncrawler.board.Cell;
import fr.campus.guitarian.dungeoncrawler.characters.Character;
import fr.campus.guitarian.dungeoncrawler.characters.enemies.Dragon;
import fr.campus.guitarian.dungeoncrawler.characters.enemies.Goblin;
import fr.campus.guitarian.dungeoncrawler.characters.enemies.Sorcerer;
import fr.campus.guitarian.dungeoncrawler.characters.types.Warrior;
import fr.campus.guitarian.dungeoncrawler.characters.types.Wizard;
import fr.campus.guitarian.dungeoncrawler.combat.CombatManager;
import fr.campus.guitarian.dungeoncrawler.combat.CombatOutcome;
import fr.campus.guitarian.dungeoncrawler.db.CharacterDAO;
//import fr.campus.guitarian.dungeoncrawler.db.CharacterRow;
import fr.campus.guitarian.dungeoncrawler.dice.Dice;
import fr.campus.guitarian.dungeoncrawler.exceptions.OutOfBoardException;
import fr.campus.guitarian.dungeoncrawler.items.defensive.GrandPotion;
//import fr.campus.guitarian.dungeoncrawler.items.defensive.Shield;
import fr.campus.guitarian.dungeoncrawler.items.defensive.StandardPotion;
import fr.campus.guitarian.dungeoncrawler.items.offensive.*;

import java.io.IOException;
//import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.*;

public class Game {

    private Character character;
    private final Menu menu;
    private CharacterDAO characterDAO;
    private int playerPosition;
    private int boardSize;
    private List<Cell> board;

    private Dice sixSidedDice;
    //private Dice twentySidedDice;
    private CombatManager combatManager;

    public Game(Menu menu, int playerPosition, int boardSize, CharacterDAO characterDAO, Dice sixSidedDice, CombatManager combatManager) throws SQLException, IOException, OutOfBoardException {

        this.menu = menu;
        this.playerPosition = playerPosition;
        this.boardSize = boardSize;
        this.characterDAO = characterDAO;

        this.sixSidedDice = sixSidedDice;
        //this.twentySidedDice = twentySidedDice;
        this.combatManager = combatManager;

        this.initializeBoard();
    }

    private void initializeBoard() throws OutOfBoardException{
        this.board = new ArrayList<Cell>();

        //Add First 64 empty cells
        for (int i = 0; i < this.boardSize; i++) {
            this.board.add(new Cell());
        }

        List<Integer> randomPositions = new ArrayList<>();
        for(int i = 0; i < this.boardSize; i++){
            randomPositions.add(i);
        }
        Collections.shuffle(randomPositions);

        int index = 0; // Common counter
        //Init each parts Quantity
        int dragonsQty = 4;
        int sorcerersQty = 10;
        int goblinsQty = 10;
        int macesQty = 5;
        int swordsQty = 4;
        int lightningsQty = 5;
        int fireballsQty = 2;
        int standardPotionsQty = 6;
        int grandPotionsQty = 2;
        //int emptyCellsQty = 16;

        for (int i = 0; i < dragonsQty; i++) {
            board.get(randomPositions.get(index)).setCharacter(new Dragon("Dragon_" + (i+1)));
            index++;
        }
        for (int i = 0; i < sorcerersQty; i++) {
            board.get(randomPositions.get(index)).setCharacter(new Sorcerer("Sorcerer_" + (i+1)));
            index++;
        }
        for (int i = 0; i < goblinsQty; i++) {
            board.get(randomPositions.get(index)).setCharacter(new Goblin("Goblin_" + (i+1)));
            index++;
        }
        for (int i = 0; i < macesQty; i++) {
            board.get(randomPositions.get(index)).setOffensiveEquipment(new Mace("Mace_" + (i+1)));
            index++;
        }
        for (int i = 0; i < swordsQty; i++) {
            board.get(randomPositions.get(index)).setOffensiveEquipment(new Sword("Sword_" + (i+1)));
            index++;
        }
        for (int i = 0; i < lightningsQty; i++) {
            board.get(randomPositions.get(index)).setOffensiveEquipment(new Lightning("Lightning_" + (i+1)));
            index++;
        }
        for (int i = 0; i < fireballsQty; i++) {
            board.get(randomPositions.get(index)).setOffensiveEquipment(new Fireball("Fireball_" + (i+1)));
            index++;
        }
        for (int i = 0; i < standardPotionsQty; i++) {
            board.get(randomPositions.get(index)).setDefensiveEquipment(new StandardPotion("StandardPotion_" + (i+1)));
            index++;
        }
        for (int i = 0; i < grandPotionsQty; i++) {
            board.get(randomPositions.get(index)).setDefensiveEquipment(new GrandPotion("GrandPotion_" + (i+1)));
            index++;
        }
    }

    public void start()
    {
        boolean isCharCreated = this.createCharacter() == 1;

        if(isCharCreated){
            int playerChoice = 0;

            //While choice is not "continue" then we must process with "Edit Player" or "Display Info"
            while (playerChoice != 3){
                playerChoice = menu.getCharacterSubMenu();

                if(playerChoice == 1){ //Display Info
                    System.out.print("\n" + this.character);
                }
                else if(playerChoice == 2){
                    menu.editCharacterName(this.character);
                }
            }
            // Player choice is now "Continue"
            System.out.print("CURRENT POSITION = " + this.getPlayerPosition() + "/" + this.getBoardSize() + "\n");
            while (true)
            {
                try{
                    this.playTurn();
                }
                catch (OutOfBoardException outExp){
                    System.out.print(outExp.getMessage());
                    break;
                }
            }
        }
        else{
            System.out.print("GAME OVER !");
        }
    }


    public void playTurn() throws OutOfBoardException{

        int diceValue = this.sixSidedDice.roll();
        System.out.print("DICE RESULT = " + diceValue + " ===> ");
        int newPosition = this.getPlayerPosition() + diceValue;
        this.setPlayerPosition(newPosition);
        if(newPosition == this.getBoardSize()){
            throw new OutOfBoardException("FLAWLESS VICTORY !!!");
        }
        else if(newPosition > this.getBoardSize()){
            throw new OutOfBoardException("VICTORY !!!");
        }

        System.out.print("CURRENT POSITION = " + newPosition + "/" + this.getBoardSize() + "\n");

        Cell currentCell = this.board.get(newPosition-1);
        CombatOutcome combatOutcome = currentCell.interact(this.character, this.combatManager);

        if(combatOutcome.getEnemyDefeated()){
            System.out.println("Enemy '" + currentCell.getCharacter().getName() + "' is DEFEATED ! " + "Your current HP = " + this.character.getHealthPoint());
            currentCell.setCharacter(null);
        }
        else if(combatOutcome.getPlayerDefeated()){
            System.out.println("YOU HAVE BEEN DEFEATED !!!");
            throw new OutOfBoardException("GAME OVER"); //To stop the GAME
        }
        else if(combatOutcome.getPlayerFled()){
            int retreatDistance = combatOutcome.getRetreatDistance();
            System.out.println("You are moving back " + retreatDistance + " cell(s) ");
            int retreatPosition = Math.max(1, this.getPlayerPosition() - retreatDistance); //Position 1 is the beginning
            this.setPlayerPosition(retreatPosition);
            System.out.print("RETREAT POSITION = " + this.getPlayerPosition() + "/" + this.getBoardSize() + "\n");
            //No more interaction for escapers !!!
        }
    }



    public int createCharacter(){
        int startChoice = menu.displayWelcomeMessage();
        if(startChoice == 1){
            String charName = menu.getCharacterChoiceString("Choose your character's name ? \n>");
            System.out.print("Welcome " + charName + " !\n");

            int charType = 0;
            while(charType!=1 && charType!=2)
            {
                charType = menu.getCharacterChoiceInt("Choose your character's type: \n1. Warrior \n2. Wizard \n>");
                if(charType == 1){ // Warrior
                    this.character = new Warrior(charName);
                }
                else if (charType == 2){ // Wizard
                    this.character = new Wizard(charName);
                }
            }
        }
        return startChoice;
    }

    // Méthodes BDD (getHeroesFromDB, createHeroInDB, editHeroInDB, changeHeroLifePointInDB)
    // moved to CharacterDAO. Use here this.characterDAO.xxx(...) if needed.

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    public int getBoardSize() {
        return this.board.size();
    }


    @Override
    public String toString() {
        return "Game{" +
                "character=" + character +
                ", boardSize=" + this.getBoardSize() +
                ", currentPosition=" + playerPosition +
                '}';
    }
}