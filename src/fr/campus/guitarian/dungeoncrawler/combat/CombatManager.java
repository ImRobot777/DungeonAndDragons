package fr.campus.guitarian.dungeoncrawler.combat;

import fr.campus.guitarian.dungeoncrawler.characters.Character;
import fr.campus.guitarian.dungeoncrawler.characters.enemies.Enemy;
import fr.campus.guitarian.dungeoncrawler.core.Menu;
import fr.campus.guitarian.dungeoncrawler.dice.Dice;

public class CombatManager {
    private Dice sixSidedDice;
    private Dice twentySidedDice;
    private final Menu menu;

    public CombatManager(Dice sixSidedDice, Dice twentySidedDice,  Menu menu) {
        this.sixSidedDice = sixSidedDice;
        this.twentySidedDice = twentySidedDice;
        this.menu = menu;
    }

    public CombatOutcome fight(Character player, Enemy enemy) {
        //init
        CombatOutcome combatOutcome = new CombatOutcome(false, false, false, 0);

        int playerOffEqpAttack = player.getOffensiveEquipment() != null ? player.getOffensiveEquipment().getAttackLevel() : 0;
        int totalPlayerAttack = player.getAttackPoint() + playerOffEqpAttack;

        System.out.println("Enemy '" + enemy.getName() + "' HP = " + enemy.getHealthPoint());
        while(true){
            int playerChoice = menu.getCharacterChoiceInt("What do you want ?: \n1. Escape \n2. FIGHT ! \n>");
            // Player Escaping
            if (playerChoice == 1){
                combatOutcome.setPlayerFled(true);
                combatOutcome.setRetreatDistance(sixSidedDice.roll());
                System.out.println("You FLED (^_^)");
                break;
            }
            else{
                //player attacking
                System.out.println("You are Attacking !");
                int finalPlayerAttack = this.applyCriticalRule(totalPlayerAttack);
                enemy.setHealthPoint(enemy.getHealthPoint() - finalPlayerAttack);
                System.out.println("Enemy '" + enemy.getName() + "' remaining HP = " + enemy.getHealthPoint());
                if (enemy.getHealthPoint() <= 0) {
                    combatOutcome.setEnemyDefeated(true);
                    break;
                }
                else{ //Enemy is attacking the player
                    System.out.println("Enemy is Attacking !");
                    int finalEnemyAttack = this.applyCriticalRule(enemy.getAttackPoint());
                    player.setHealthPoint(player.getHealthPoint() - finalEnemyAttack);
                    System.out.println("Your remaining HP = '" + player.getHealthPoint());
                    if(player.getHealthPoint() <= 0){
                        combatOutcome.setPlayerDefeated(true);
                        break;
                    }
                }
            }
        }

        return combatOutcome;
    }

    private int applyCriticalRule(int baseAttack){
        int rollRes = twentySidedDice.roll();
        int finalAttack;

        if(rollRes == 1){
            finalAttack = 0;
            System.out.println("twentySidedDice ==> 1 (HA HA HA)");
        }
        else if(rollRes == 20){
            finalAttack = baseAttack + 2;
            System.out.println("twentySidedDice ==> 20 (Nice !)");
        }
        else {
            finalAttack = baseAttack;
        }

        return finalAttack;
    }

}
