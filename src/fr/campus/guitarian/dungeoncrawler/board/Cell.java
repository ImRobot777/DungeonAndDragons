package fr.campus.guitarian.dungeoncrawler.board;

import fr.campus.guitarian.dungeoncrawler.characters.Character;
import fr.campus.guitarian.dungeoncrawler.characters.types.Warrior;
import fr.campus.guitarian.dungeoncrawler.characters.types.Wizard;
import fr.campus.guitarian.dungeoncrawler.items.DefensiveEquipment;
import fr.campus.guitarian.dungeoncrawler.items.OffensiveEquipment;
import fr.campus.guitarian.dungeoncrawler.items.offensive.Spell;
import fr.campus.guitarian.dungeoncrawler.items.offensive.Weapon;

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


    public void interact(Character player){
        if(this.character != null){
            System.out.println("ENCOUNTER ! ==> '" + this.character.getClass().getSimpleName() + "'");
            //TODO CODE THE FIGHT

        } else if (this.offensiveEquipment!=null) {
            String eqpDesc = "'" + this.offensiveEquipment.getName() + "' with AP= " + this.offensiveEquipment.getAttackLevel() + " ";
            Boolean playerIsWarriorAndEqpIsWeapon = player instanceof Warrior && this.offensiveEquipment instanceof Weapon;
            Boolean playerIsWizardAndEqpIsSpell = player instanceof Wizard && this.offensiveEquipment instanceof Spell;
            if(playerIsWarriorAndEqpIsWeapon ||  playerIsWizardAndEqpIsSpell){
                Boolean isLootBetterThanPlayersOne =
                         (player.getOffensiveEquipment() != null
                            && player.getOffensiveEquipment().getAttackLevel() < this.offensiveEquipment.getAttackLevel())
                         || player.getOffensiveEquipment() == null
                ;
                if ( isLootBetterThanPlayersOne ){
                    player.setOffensiveEquipment(this.offensiveEquipment);
                    System.out.println("You got: " + eqpDesc);
                }
                else{
                    System.out.println("Equipment ignored, no good enough: " +  eqpDesc);
                }
            }
            else {
                System.out.println("You cannot use: " + eqpDesc);
            }
        }else if(this.defensiveEquipment != null){
            String eqpDesc = "'" + this.defensiveEquipment.getName() + "' with HP= " + this.defensiveEquipment.getDefenseLevel() + " ";
            System.out.println("You got: " + eqpDesc);
            int totalHP = player.getHealthPoint() +  this.defensiveEquipment.getDefenseLevel();
            player.setHealthPoint(totalHP);
            System.out.println("Your HP is now: " + totalHP);
        }
        else {// Empty cell
            System.out.println("Empty cell, nothing happened");
        }
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
