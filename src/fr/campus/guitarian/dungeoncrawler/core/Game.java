package fr.campus.guitarian.dungeoncrawler.core;

import fr.campus.guitarian.dungeoncrawler.board.Cell;
import fr.campus.guitarian.dungeoncrawler.characters.Character;
import fr.campus.guitarian.dungeoncrawler.characters.types.Warrior;
import fr.campus.guitarian.dungeoncrawler.characters.types.Wizard;
import fr.campus.guitarian.dungeoncrawler.exceptions.OutOfBoardException;
import fr.campus.guitarian.dungeoncrawler.items.defensive.Shield;
import fr.campus.guitarian.dungeoncrawler.items.offensive.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private Character character;
    private Menu menu = Menu.getInstance();

    private int playerPosition;
    private List<Cell> board;

    public Game() {
        this.playerPosition = 1;
        this.initializeBoard();
    }

    private void initializeBoard(){
        this.board = new ArrayList<Cell>();

        //Add First cell empty
        this.board.add(new Cell());

        //Create and add 2nd cell with offensive equipment
        Cell cellOfEq = new Cell();
        cellOfEq.setOffensiveEquipment(new Weapon("WEAP TEST", 10));
        this.board.add(cellOfEq);

        //Add 3rd cell empty
        this.board.add(new Cell());

        //Create and add 4th cell with defensive equipment
        Cell cellDeEq = new Cell();
        cellDeEq.setDefensiveEquipment(new Shield("SHIELD TEST", 10));
        this.board.add(cellDeEq);

        //Add 5th cell empty
        this.board.add(new Cell());

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
            // On est sorti du while <==> le joueur a choisi "Continue"
            // On lance le dé et on change la position
            //int diceValue = Math.round( 6 * Math.random() + 1);
            System.out.print("CURRENT POSITION = " + this.getPlayerPosition() + "/" + this.getBoardSize() + "\n");
            Random random = new Random();
            while (true)
            {
                try{
                    this.playTurn(random);
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


    public void playTurn(Random random) throws OutOfBoardException{
        int diceValue;
        //diceValue= random.nextInt(6) + 1;
        diceValue = 1; //cheat dice

        System.out.print("DICE RESULT = " + diceValue + " ===> ");
        int newPosition = this.getPlayerPosition()+diceValue;
        this.setPlayerPosition(newPosition);
        if(newPosition == this.getBoardSize()){
            throw new OutOfBoardException("FLAWLESS VICTORY !!!");
        }
        else if(newPosition > this.getBoardSize()){
            throw new OutOfBoardException("VICTORY !!!");
        }
        System.out.print("CURRENT POSITION = " + newPosition + "/" + this.getBoardSize() + "\n");
        System.out.print(this.board.get(newPosition-1) + "\n");
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
                    //this.character.setOffensiveEquipment(new Weapon("LEGENDARY SWORD", 100));
                    //this.character.setDefensiveEquipment(new Shield("LEGENDARY ARMOR", 100));
                }
                else if (charType == 2){ // Wizard
                    this.character = new Wizard(charName);
                    //this.character.setOffensiveEquipment(new Spell("FIRE ANNIHILATION", 100));
                    //this.character.setDefensiveEquipment(new Potion("LEGENDARY HEAL", 100));
                }
            }
        }
        return startChoice;
    }

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
