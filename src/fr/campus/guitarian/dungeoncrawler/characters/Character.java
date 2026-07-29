package fr.campus.guitarian.dungeoncrawler.characters;

import fr.campus.guitarian.dungeoncrawler.items.DefensiveEquipment;
import fr.campus.guitarian.dungeoncrawler.items.OffensiveEquipment;

public abstract class Character {
    private String name;
    private int healthPoint;
    private int attackPoint;
    private OffensiveEquipment offensiveEquipment;
    private DefensiveEquipment defensiveEquipment;
    private Integer maxHealthPoint;

    private int id; //Only for heroes from the DataBase

    protected Character(String name, int hp, int ap, OffensiveEquipment offensiveEquipment, DefensiveEquipment defensiveEquipment, Integer maxHealthPoint){
        this.name = name;
        this.healthPoint = hp;
        this.attackPoint = ap;
        this.offensiveEquipment = offensiveEquipment;
        this.defensiveEquipment = defensiveEquipment;
        this.maxHealthPoint = maxHealthPoint;
    }

    public Integer getMaxHealthPoint() {
        return this.maxHealthPoint;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int bdd_id){
        this.id = bdd_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
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
