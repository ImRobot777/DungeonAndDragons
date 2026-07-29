package fr.campus.guitarian.dungeoncrawler.combat;

import fr.campus.guitarian.dungeoncrawler.characters.Character;
import fr.campus.guitarian.dungeoncrawler.characters.enemies.Enemy;
import fr.campus.guitarian.dungeoncrawler.dice.Dice;

public class CombatManager {
    private Dice sixSidedDice;
    private Dice twentySidedDice;
    //private final Menu menu;

    public CombatManager(Dice sixSidedDice, Dice twentySidedDice) {
        //this.menu = menu;
        this.sixSidedDice = sixSidedDice;
        this.twentySidedDice = twentySidedDice;
    }

    public CombatOutcome fight(Character player, Enemy enemy) {
        //init
        CombatOutcome combatOutcome = new CombatOutcome(false, false, false, 0);

        int playerOffEqpAttack = player.getOffensiveEquipment() != null ? player.getOffensiveEquipment().getAttackLevel() : 0;
        int totalPlayerAttack = player.getAttackPoint() + playerOffEqpAttack;

        //player attacking
        enemy.setHealthPoint(enemy.getHealthPoint() - totalPlayerAttack);
        if (enemy.getHealthPoint() <= 0) {
            combatOutcome.setEnemyDefeated(true);
        }
        else{ //Enemy is attacking the player
            player.setHealthPoint(player.getHealthPoint() - enemy.getAttackPoint());
            if(player.getHealthPoint() <= 0){
                combatOutcome.setPlayerDefeated(true);
            }
        }


        return combatOutcome;
    }

}
