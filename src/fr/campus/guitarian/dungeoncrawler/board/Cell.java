package fr.campus.guitarian.dungeoncrawler.board;

import fr.campus.guitarian.dungeoncrawler.characters.Character;
import fr.campus.guitarian.dungeoncrawler.characters.enemies.Enemy;
import fr.campus.guitarian.dungeoncrawler.combat.CombatManager;
import fr.campus.guitarian.dungeoncrawler.combat.CombatOutcome;
import fr.campus.guitarian.dungeoncrawler.items.DefensiveEquipment;
import fr.campus.guitarian.dungeoncrawler.items.OffensiveEquipment;

/**
 * The type Cell.
 */
public class Cell {

    private Character character;
    private OffensiveEquipment offensiveEquipment;
    private DefensiveEquipment defensiveEquipment;

    /**
     * Instantiates a new Cell.
     */
    public Cell(){
        this.character = null;
        this.offensiveEquipment = null;
        this.defensiveEquipment = null;
    }

    /**
     * Gets character.
     *
     * @return the character
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Sets character.
     *
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Gets offensive equipment.
     *
     * @return the offensive equipment
     */
    public OffensiveEquipment getOffensiveEquipment() {
        return offensiveEquipment;
    }

    /**
     * Sets offensive equipment.
     *
     * @param offensiveEquipment the offensive equipment
     */
    public void setOffensiveEquipment(OffensiveEquipment offensiveEquipment) {
        this.offensiveEquipment = offensiveEquipment;
    }

    /**
     * Gets defensive equipment.
     *
     * @return the defensive equipment
     */
    public DefensiveEquipment getDefensiveEquipment() {
        return defensiveEquipment;
    }

    /**
     * Sets defensive equipment.
     *
     * @param defensiveEquipment the defensive equipment
     */
    public void setDefensiveEquipment(DefensiveEquipment defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }


    /**
     * Interact combat outcome.
     *
     * @param player        the player
     * @param combatManager the combat manager
     * @return the combat outcome
     */
    public CombatOutcome interact(Character player, CombatManager combatManager){
        CombatOutcome combatOutcome = new CombatOutcome(false, false, false, 0);
        if(this.character != null){
            System.out.println("ENCOUNTER ! ==> '" + this.character.getName() + "' " + "Your current HP = " + player.getHealthPoint());
            combatOutcome = combatManager.fight(player, (Enemy) this.character);
        }
        else if (this.offensiveEquipment!=null) {
            String eqpDesc = "'" + this.offensiveEquipment.getName() + "' with AP= " + this.offensiveEquipment.getAttackLevel() + " ";
            if (player.canEquip(this.offensiveEquipment)) {
                Boolean isLootBetterThanPlayersOne =
                        player.getOffensiveEquipment() == null || player.getOffensiveEquipment().getAttackLevel() < this.offensiveEquipment.getAttackLevel()
                        ;
                if ( isLootBetterThanPlayersOne ){
                    player.setOffensiveEquipment(this.offensiveEquipment);
                    System.out.println("You got: " + eqpDesc + " your AP are now: " + (player.getAttackPoint()+this.offensiveEquipment.getAttackLevel()));
                }
                else{System.out.println("Equipment ignored, no good enough: " +  eqpDesc);}
            }
            else {System.out.println("You cannot use: " + eqpDesc);}
        }
        else if(this.defensiveEquipment != null){
            String eqpDesc = "'" + this.defensiveEquipment.getName() + "' with HP= " + this.defensiveEquipment.getDefenseLevel() + " ";
            System.out.println("You got: " + eqpDesc);
            int totalHP = Math.min(player.getMaxHealthPoint(), player.getHealthPoint() +  this.defensiveEquipment.getDefenseLevel());
            player.setHealthPoint(totalHP);
            System.out.println("Your HP is now: " + totalHP);
        }
        else {System.out.println("Empty cell, nothing happened");}
        return combatOutcome;
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