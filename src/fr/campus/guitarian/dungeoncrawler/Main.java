package fr.campus.guitarian.dungeoncrawler;

public class Main {

    public static void main(String[] args) {
        //Game game = new Game();
        //game.start();

        //Menu menu = new Menu(); <== Not possible (Singleton)
        Menu menu = Menu.getInstance();
        menu.displayWelcomeMessage();
        menu.getCharacterChoiceString("Your character's name ?");
        menu.getCharacterSubMenu();
    }
}