package fr.campus.guitarian.dungeoncrawler.core;

import fr.campus.guitarian.dungeoncrawler.board.Cell;
import fr.campus.guitarian.dungeoncrawler.characters.Character;
import fr.campus.guitarian.dungeoncrawler.characters.enemies.Dragon;
import fr.campus.guitarian.dungeoncrawler.characters.enemies.Goblin;
import fr.campus.guitarian.dungeoncrawler.characters.enemies.Sorcerer;
import fr.campus.guitarian.dungeoncrawler.characters.types.Warrior;
import fr.campus.guitarian.dungeoncrawler.characters.types.Wizard;
import fr.campus.guitarian.dungeoncrawler.db.CharacterDAO;
import fr.campus.guitarian.dungeoncrawler.db.CharacterRow;
import fr.campus.guitarian.dungeoncrawler.exceptions.OutOfBoardException;
import fr.campus.guitarian.dungeoncrawler.items.defensive.GrandPotion;
import fr.campus.guitarian.dungeoncrawler.items.defensive.Shield;
import fr.campus.guitarian.dungeoncrawler.items.defensive.StandardPotion;
import fr.campus.guitarian.dungeoncrawler.items.offensive.*;

import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private Character character;
    private final Menu menu = Menu.getInstance();

    private int playerPosition;
    private List<Cell> board;

    private CharacterDAO characterDAO = new CharacterDAO() ;

    public Game() throws SQLException, IOException {
        this.playerPosition = 1;
        this.initializeBoard();
    }

    private void initializeBoard(){
        this.board = new ArrayList<Cell>();

        //Add First cell empty
        this.board.add(new Cell());

        Cell cellSword = new Cell();
        cellSword.setOffensiveEquipment(new Sword());
        this.board.add(cellSword);

        Cell cellMace = new Cell();
        cellMace.setOffensiveEquipment(new Mace());
        this.board.add(cellMace);

        Cell goblinCell = new Cell();
        goblinCell.setCharacter(new Goblin("GobGob"));
        this.board.add(goblinCell);

        Cell cellPotion= new Cell();
        cellPotion.setDefensiveEquipment(new GrandPotion());
        this.board.add(cellPotion);

        Cell cellPotionLow= new Cell();
        cellPotionLow.setDefensiveEquipment(new StandardPotion());
        this.board.add(cellPotionLow);

        Cell cellFireBall = new Cell();
        cellFireBall.setOffensiveEquipment(new Fireball());
        this.board.add(cellFireBall);

        Cell sorcererCell = new Cell();
        sorcererCell.setCharacter(new Sorcerer("Arifureta"));
        this.board.add(sorcererCell);

        Cell cellSpell = new Cell();
        cellSpell.setOffensiveEquipment(new Lightning());
        this.board.add(cellSpell);

        Cell dragonCell = new Cell();
        dragonCell.setCharacter(new Dragon("Smaug"));
        this.board.add(dragonCell);

        // TEMP
        /*
        int i = 0;
        for (Cell cell : this.board) {
            i+=1;
            System.out.println(i + "/ " + cell.toString());
        }
        */

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

    public List<Character> getHeroesFromDB() throws SQLException{
        List<Character> heroes = new ArrayList<>();
        List<CharacterRow> rows = this.characterDAO.getCharactersDAO();
        for (CharacterRow row : rows) {
            Character character;
            if (row.getType().equals("warrior")){
                character = new Warrior(row.getName());
            } else if (row.getType().equals("wizard")) {
                character = new Wizard(row.getName());
            }
            else{
                throw new SQLDataException("Data Base Characters Type ERROR");
            }
            character.setId(row.getId());
            character.setHealthPoint(row.getLifePoints());
            character.setAttackPoint(row.getAttackPoints());

            //For the moment Defensive and Offensive Equipment are not handled
            //character.setDefensiveEquipment();
            //character.setOffensiveEquipment();
            heroes.add(character);
        }
        return heroes;
    }

    public void createHeroInDB(Character c) throws SQLException{
        CharacterRow cr;
        if(c instanceof Warrior){
            cr = new CharacterRow(0, "Warrior",
                c.getName(), c.getHealthPoint(), c.getAttackPoint(),
                c.getOffensiveEquipment() != null ? c.getOffensiveEquipment().toString() : null,
                c.getDefensiveEquipment() != null ? c.getDefensiveEquipment().toString() : null
            );
        }
        else if(c instanceof Wizard){
            cr = new CharacterRow(0, "Wizard",
                c.getName(), c.getHealthPoint(), c.getAttackPoint(),
                c.getOffensiveEquipment() != null ? c.getOffensiveEquipment().toString() : null,
                c.getDefensiveEquipment() != null ? c.getDefensiveEquipment().toString() : null
            );
        }
        else{
            throw new SQLDataException("Character's Type ERROR");
        }
        //Save in BDD and get Id (in BDD back)
        c.setId(characterDAO.setCharactersDAO(cr));
    }

    public void editHeroInDB(Character c) throws SQLException{
        CharacterRow cr;
        if(c instanceof Warrior){
            cr = new CharacterRow(c.getId(), "Warrior",
                c.getName(), c.getHealthPoint(), c.getAttackPoint(),
                c.getOffensiveEquipment() != null ? c.getOffensiveEquipment().toString() : null,
                c.getDefensiveEquipment() != null ? c.getDefensiveEquipment().toString() : null
            );
        }
        else if(c instanceof Wizard){
            cr = new CharacterRow(c.getId(), "Wizard",
                c.getName(), c.getHealthPoint(), c.getAttackPoint(),
                c.getOffensiveEquipment() != null ? c.getOffensiveEquipment().toString() : null,
                c.getDefensiveEquipment() != null ? c.getDefensiveEquipment().toString() : null
            );
        }else{
            throw new SQLDataException("Character's Type ERROR");
        }
        characterDAO.editCharactersDAO(cr);
    }

    public void changeHeroLifePointInDB(Character c) throws SQLException{
        characterDAO.editLifePointsDAO(c.getHealthPoint(), c.getId());
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
