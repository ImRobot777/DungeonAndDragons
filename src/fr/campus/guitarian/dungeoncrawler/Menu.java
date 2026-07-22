package fr.campus.guitarian.dungeoncrawler;

import java.util.Scanner;

///
// EN MODE SINGLETON //1 seule et même instance utilisable dans tout le Game
public class Menu {

    private Scanner scanner = new Scanner(System.in);

    // Singleton <==> 2 declaration below
    private static final Menu INSTANCE = new Menu();
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
     */
    public String getCharacterChoiceString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Ask a question to the player
     * AND return his choice as integer
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


    public int displayWelcomeMessage(){
        return this.getCharacterChoiceInt("Welcome in the best game ever ! \n1. New Character \n2. Exit \n>");
    }


    public int getCharacterSubMenu(){
        return this.getCharacterChoiceInt("\n1. Display Info \n2. Edit Player \n3. Continue \n>");
    }


    public void displayCharacterInfo(Character c){
        System.out.println(c);
    }

    public void editCharacterName(Character c){
        String newName = getCharacterChoiceString("New Name \n>");
        c.setName(newName);
        System.out.print("Your name is now " + newName + " !\n");
    }

    public boolean isPlayerStartingTheGame(){
        int choice = getCharacterChoiceInt("1. Start \n2. Return \n>");
        return choice == 1;
    }


}
