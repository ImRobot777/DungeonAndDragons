package fr.campus.guitarian.dungeoncrawler.items;

/**
 * The type Offensive equipment.
 */
public abstract class OffensiveEquipment {

    private String name;
    private int attackLevel;

    /**
     * Instantiates a new Offensive equipment.
     *
     * @param name        the name
     * @param attackLevel the attack level
     */
    protected OffensiveEquipment(String name, int attackLevel) {
        this.name = name;
        this.attackLevel = attackLevel;
    }

    /**
     * Gets attack level.
     *
     * @return the attack level
     */
    public int getAttackLevel() {
        return attackLevel;
    }

    /**
     * Sets attack level.
     *
     * @param attackLevel the attack level
     */
    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() + " ==> {" +
                "'" + name + "' : " +
                "ATK = " + attackLevel +
                '}';
    }
}
