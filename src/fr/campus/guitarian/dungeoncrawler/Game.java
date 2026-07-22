package fr.campus.guitarian.dungeoncrawler;

import java.util.Random;

public class Game {

    private Character character;
    private Menu menu = Menu.getInstance();

    private int currentPosition;
    private int boardSize; //64

    public Game(int boardSize, int startPosition) {
        this.boardSize = boardSize;
        this.currentPosition = startPosition;
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
            Random random = new Random();
            while (this.currentPosition < this.boardSize)
            {
                int diceValue = random.nextInt(6) + 1;
                System.out.print("DICE RESULT = " + diceValue);
                int newPosition = this.getCurrentPosition()+diceValue;
                this.setCurrentPosition(newPosition);
                System.out.print("CURRENT POSITION = " + newPosition + " / " + this.boardSize + "\n");
            }
            System.out.print("VICTORY !");

        }
        else{
            System.out.print("GAME OVER !");
        }

    }

    public int createCharacter(){
        int startChoice = menu.displayWelcomeMessage();
        if(startChoice == 1){
            String charName = menu.getCharacterChoiceString("Choose your character's name ? \n>");
            System.out.print("Welcome " + charName + " !\n");
            int charType = menu.getCharacterChoiceInt("Choose your character's type: \n1. Warrior \n2. Wizard \n>");
            if(charType == 1){ // Warrior
                this.character = new Warrior(charName);
            }
            else{ // Wizard
                this.character = new Wizard(charName);
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

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    @Override
    public String toString() {
        return "Game{" +
                "character=" + character +
                ", boardSize=" + boardSize +
                ", currentPosition=" + currentPosition +
                '}';
    }
}
