package fr.campus.guitarian.dungeoncrawler.core;

import fr.campus.guitarian.dungeoncrawler.characters.Character;

import java.util.Scanner;

/**
 * The type Menu.
 */
// EN MODE SINGLETON //1 seule et même instance utilisable dans tout le Game
public class Menu {

    private Scanner scanner = new Scanner(System.in);

    // Singleton <==> 2 declaration below
    private static final Menu INSTANCE = new Menu();

    /**
     * Get instance menu.
     *
     * @return the menu
     */
    public static Menu getInstance(){
        return INSTANCE;
    }

    private Menu(){
        //Private Constructor mandatory in order to get only 1 INSTANCE
        // In order to not allow Menu menu = new Menu();
    }

    /**
     * Ask a question to the player
     * AND return his choice as String
     *
     * @param prompt the prompt
     * @return the character choice string
     */
    public String getCharacterChoiceString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Ask a question to the player
     * AND return his choice as integer
     *
     * @param prompt the prompt
     * @return the character choice int
     */
    public int getCharacterChoiceInt(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Please type a valid number");
            }
        }
        return value;
    }


    /**
     * Display welcome message int.
     *
     * @return the int
     */
    public int displayWelcomeMessage(){
        return this.getCharacterChoiceInt("Welcome in the best game ever ! \n1. New Character \n2. Exit \n>");
    }


    /**
     * Get character sub menu int.
     *
     * @return the int
     */
    public int getCharacterSubMenu(){
        return this.getCharacterChoiceInt("\n1. Display Info \n2. Edit Player \n3. Continue \n>");
    }


    /**
     * Display character info.
     *
     * @param c the c
     */
    public void displayCharacterInfo(fr.campus.guitarian.dungeoncrawler.characters.Character c){
        System.out.println(c);
    }

    /**
     * Edit character name.
     *
     * @param c the c
     */
    public void editCharacterName(Character c){
        String newName = getCharacterChoiceString("New Name \n>");
        c.setName(newName);
        System.out.print("Your name is now " + newName + " !\n");
    }

    /**
     * Is player starting the game boolean.
     *
     * @return the boolean
     */
    public boolean isPlayerStartingTheGame(){
        int choice = getCharacterChoiceInt("1. Start \n2. Return \n>");
        return choice == 1;
    }


}
