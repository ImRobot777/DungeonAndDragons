package fr.campus.guitarian.dungeoncrawler.combat;


/**
 * The type Combat outcome.
 */
public class CombatOutcome {

    private Boolean enemyDefeated;
    private Boolean playerDefeated;
    private Boolean playerFled;
    private int retreatDistance;

    /**
     * Instantiates a new Combat outcome.
     *
     * @param enemyDefeated   the enemy defeated
     * @param playerDefeated  the player defeated
     * @param playerFled      the player fled
     * @param retreatDistance the retreat distance
     */
    public CombatOutcome(Boolean enemyDefeated, Boolean playerDefeated, Boolean playerFled, int retreatDistance) {
        this.enemyDefeated = enemyDefeated;
        this.playerDefeated = playerDefeated;
        this.playerFled = playerFled;
        this.retreatDistance = retreatDistance;
    }

    /**
     * Gets enemy defeated.
     *
     * @return the enemy defeated
     */
    public Boolean getEnemyDefeated() {
        return enemyDefeated;
    }

    /**
     * Sets enemy defeated.
     *
     * @param enemyDefeated the enemy defeated
     */
    public void setEnemyDefeated(Boolean enemyDefeated) {
        this.enemyDefeated = enemyDefeated;
    }

    /**
     * Gets player defeated.
     *
     * @return the player defeated
     */
    public Boolean getPlayerDefeated() {
        return playerDefeated;
    }

    /**
     * Sets player defeated.
     *
     * @param playerDefeated the player defeated
     */
    public void setPlayerDefeated(Boolean playerDefeated) {
        this.playerDefeated = playerDefeated;
    }

    /**
     * Gets player fled.
     *
     * @return the player fled
     */
    public Boolean getPlayerFled() {
        return playerFled;
    }

    /**
     * Sets player fled.
     *
     * @param playerFled the player fled
     */
    public void setPlayerFled(Boolean playerFled) {
        this.playerFled = playerFled;
    }

    /**
     * Gets retreat distance.
     *
     * @return the retreat distance
     */
    public int getRetreatDistance() {
        return retreatDistance;
    }

    /**
     * Sets retreat distance.
     *
     * @param retreatDistance the retreat distance
     */
    public void setRetreatDistance(int retreatDistance) {
        this.retreatDistance = retreatDistance;
    }
}
