package fr.campus.guitarian.dungeoncrawler.items;

/**
 * The type Defensive equipment.
 */
public abstract class DefensiveEquipment {

    private String name;
    private int defenseLevel;

    /**
     * Instantiates a new Defensive equipment.
     *
     * @param name         the name
     * @param defenseLevel the defense level
     */
    protected DefensiveEquipment(String name, int defenseLevel) {
        this.name = name;
        this.defenseLevel = defenseLevel;
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

    /**
     * Gets defense level.
     *
     * @return the defense level
     */
    public int getDefenseLevel() {
        return defenseLevel;
    }

    /**
     * Sets defense level.
     *
     * @param defenseLevel the defense level
     */
    public void setDefenseLevel(int defenseLevel) {
        this.defenseLevel = defenseLevel;
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() + " ==> {" +
                "'" + name + "' : " +
                "DEF = " + defenseLevel +
                '}';
    }
}
