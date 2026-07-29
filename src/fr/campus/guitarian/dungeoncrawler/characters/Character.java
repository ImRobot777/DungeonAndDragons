package fr.campus.guitarian.dungeoncrawler.characters;

import fr.campus.guitarian.dungeoncrawler.items.DefensiveEquipment;
import fr.campus.guitarian.dungeoncrawler.items.OffensiveEquipment;

/**
 * The type Character.
 */
public abstract class Character {
    private String name;
    private int healthPoint;
    private int attackPoint;
    private OffensiveEquipment offensiveEquipment;
    private DefensiveEquipment defensiveEquipment;
    private Integer maxHealthPoint;

    private int id; //Only for heroes from the DataBase

    /**
     * Instantiates a new Character.
     *
     * @param name               the name
     * @param hp                 the hp
     * @param ap                 the ap
     * @param offensiveEquipment the offensive equipment
     * @param defensiveEquipment the defensive equipment
     * @param maxHealthPoint     the max health point
     */
    protected Character(String name, int hp, int ap, OffensiveEquipment offensiveEquipment, DefensiveEquipment defensiveEquipment, Integer maxHealthPoint){
        this.name = name;
        this.healthPoint = hp;
        this.attackPoint = ap;
        this.offensiveEquipment = offensiveEquipment;
        this.defensiveEquipment = defensiveEquipment;
        this.maxHealthPoint = maxHealthPoint;
    }

    /**
     * Gets max health point.
     *
     * @return the max health point
     */
    public Integer getMaxHealthPoint() {
        return this.maxHealthPoint;
    }

    /**
     * Get id int.
     *
     * @return the int
     */
    public int getId(){
        return this.id;
    }

    /**
     * Set id.
     *
     * @param bdd_id the bdd id
     */
    public void setId(int bdd_id){
        this.id = bdd_id;
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
     * Gets health point.
     *
     * @return the health point
     */
    public int getHealthPoint() {
        return healthPoint;
    }

    /**
     * Sets health point.
     *
     * @param healthPoint the health point
     */
    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    /**
     * Gets attack point.
     *
     * @return the attack point
     */
    public int getAttackPoint() {
        return attackPoint;
    }

    /**
     * Sets attack point.
     *
     * @param attackPoint the attack point
     */
    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
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
     * Can equip boolean.
     *
     * @param equipment the equipment
     * @return the boolean
     */
// Default behavior: an Enemy can not equip
    // Warrior/Wizard override this method ==> (polymorphisme).
    public boolean canEquip(OffensiveEquipment equipment) {
        return false;
    }

    @Override
    public String toString() {

        String off = offensiveEquipment !=null ? "\n offensiveEquipment: " + offensiveEquipment : "";
        String def = defensiveEquipment !=null ? "\n defensiveEquipment: " + defensiveEquipment : "";

        return  this.getClass().getSimpleName() + " ==> {" +
                "'" + name + "' : " +
                "HP = " + healthPoint +
                ", AP = " + attackPoint
                + off
                + def
                + '}';
    }
}