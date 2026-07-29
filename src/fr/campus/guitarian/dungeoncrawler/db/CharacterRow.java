package fr.campus.guitarian.dungeoncrawler.db;

/**
 * The type Character row.
 */
public class CharacterRow {
    private int id;
    private String type;
    private String name;
    private int lifePoints;
    private int attackPoints;
    private String offensiveEquipment;
    private String defensiveEquipment;

    /**
     * Instantiates a new Character row.
     *
     * @param id                 the id
     * @param type               the type
     * @param name               the name
     * @param lifePoints         the life points
     * @param attackPoints       the attack points
     * @param offensiveEquipment the offensive equipment
     * @param defensiveEquipment the defensive equipment
     */
    public CharacterRow(int id, String type, String name, int lifePoints, int attackPoints, String offensiveEquipment, String defensiveEquipment) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.lifePoints = lifePoints;
        this.attackPoints = attackPoints;
        this.offensiveEquipment = offensiveEquipment;
        this.defensiveEquipment = defensiveEquipment;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
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
     * Gets life points.
     *
     * @return the life points
     */
    public int getLifePoints() {
        return lifePoints;
    }

    /**
     * Sets life points.
     *
     * @param lifePoints the life points
     */
    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    /**
     * Gets attack points.
     *
     * @return the attack points
     */
    public int getAttackPoints() {
        return attackPoints;
    }

    /**
     * Sets attack points.
     *
     * @param attackPoints the attack points
     */
    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    /**
     * Gets offensive equipment.
     *
     * @return the offensive equipment
     */
    public String getOffensiveEquipment() {
        return offensiveEquipment;
    }

    /**
     * Sets offensive equipment.
     *
     * @param offensiveEquipment the offensive equipment
     */
    public void setOffensiveEquipment(String offensiveEquipment) {
        this.offensiveEquipment = offensiveEquipment;
    }

    /**
     * Gets defensive equipment.
     *
     * @return the defensive equipment
     */
    public String getDefensiveEquipment() {
        return defensiveEquipment;
    }

    /**
     * Sets defensive equipment.
     *
     * @param defensiveEquipment the defensive equipment
     */
    public void setDefensiveEquipment(String defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }
}
