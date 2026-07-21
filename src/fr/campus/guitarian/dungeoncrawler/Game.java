package fr.campus.guitarian.dungeoncrawler;

public class Game {

    private Character character;
    private Menu menu = Menu.getInstance();

    private int startPosition = 1;
    private int boardSize; //64

    public Game(int boardSize) {
        this.boardSize = boardSize;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
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
                ", menu=" + menu +
                ", startPosition=" + startPosition +
                ", boardSize=" + boardSize +
                '}';
    }


    public void createCharacter(){
        int startChoice = menu.displayWelcomeMessage();

        if(startChoice == 1){
            String charName = menu.getCharacterChoiceString("Choose your character's name ? \n>");
            //character.setName(charName); cela ne peut pas encore fonctionner
            //System.out.print("Welcome " + charName);
            int charType = menu.getCharacterChoiceInt("Choose your character's type: \n1. Warrior \n2. Wizard");
            String charTypeName;
            int healthPoint;
            int attackPoint;
            if(charType == 1){
                charTypeName = "Warrior";
                healthPoint  = 10;
                attackPoint = 5;

            }
            else{ // Forcing value to Wizard ?
                charTypeName = "Wizard";
                healthPoint  = 7;
                attackPoint = 7;
            }

            this.character = new Character(charTypeName, charName, healthPoint, attackPoint,
                                            new OffensiveEquipment("init", "init", 0),
                                            new DefensiveEquipment("init", "init", 0)
            );

        }
        else
        {
            // Exit or re-display ? welcome message or anything else ?
        }
    }


}
