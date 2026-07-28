package fr.campus.guitarian.dungeoncrawler.board;

import fr.campus.guitarian.dungeoncrawler.characters.Character;
import fr.campus.guitarian.dungeoncrawler.items.DefensiveEquipment;
import fr.campus.guitarian.dungeoncrawler.items.OffensiveEquipment;

public class Cell {

    private Character character;
    private OffensiveEquipment offensiveEquipment;
    private DefensiveEquipment defensiveEquipment;

    public Cell(){
        this.character = null;
        this.offensiveEquipment = null;
        this.defensiveEquipment = null;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public OffensiveEquipment getOffensiveEquipment() {
        return offensiveEquipment;
    }

    public void setOffensiveEquipment(OffensiveEquipment offensiveEquipment) {
        this.offensiveEquipment = offensiveEquipment;
    }

    public DefensiveEquipment getDefensiveEquipment() {
        return defensiveEquipment;
    }

    public void setDefensiveEquipment(DefensiveEquipment defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }

    @Override
    public String toString() {
        String car = character !=null ? "\n character: " + character : "";
        String off = offensiveEquipment !=null ? "\n offensiveEquipment: " + offensiveEquipment : "";
        String def = defensiveEquipment !=null ? "\n defensiveEquipment: " + defensiveEquipment : "";
        return "Cell ==> ["
                + car
                + off
                + def
                + "\n] \n";
    }

}
