package fr.campus.guitarian.dungeoncrawler.combat;


public class CombatOutcome {

    private Boolean enemyDefeated;
    private Boolean playerDefeated;
    private Boolean playerFled;
    private int retreatDistance;

    public CombatOutcome(Boolean enemyDefeated, Boolean playerDefeated, Boolean playerFled, int retreatDistance) {
        this.enemyDefeated = enemyDefeated;
        this.playerDefeated = playerDefeated;
        this.playerFled = playerFled;
        this.retreatDistance = retreatDistance;
    }

    public Boolean getEnemyDefeated() {
        return enemyDefeated;
    }

    public void setEnemyDefeated(Boolean enemyDefeated) {
        this.enemyDefeated = enemyDefeated;
    }

    public Boolean getPlayerDefeated() {
        return playerDefeated;
    }

    public void setPlayerDefeated(Boolean playerDefeated) {
        this.playerDefeated = playerDefeated;
    }

    public Boolean getPlayerFled() {
        return playerFled;
    }

    public void setPlayerFled(Boolean playerFled) {
        this.playerFled = playerFled;
    }

    public int getRetreatDistance() {
        return retreatDistance;
    }

    public void setRetreatDistance(int retreatDistance) {
        this.retreatDistance = retreatDistance;
    }
}
