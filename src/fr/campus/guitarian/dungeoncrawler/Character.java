package fr.campus.guitarian.dungeoncrawler;

public class Character {
    private String type="Settler";
    private String name="Jean Bono";
    private int healthPoint = 2;
    private int attackPoint = 1;
    private OffensiveEquipment offensiveEquipment = new OffensiveEquipment("INIT", "INIT", 5);
    private DefensiveEquipment defensiveEquipment = new DefensiveEquipment("INIT", "INIT", 5);

    public Character(String type, String name, int hp, int ap, OffensiveEquipment offensiveEquipment, DefensiveEquipment defensiveEquipment){
        this.type = type;
        this.name = name;
        this.healthPoint = hp;
        this.attackPoint = ap;
        this.offensiveEquipment = offensiveEquipment;
        this.defensiveEquipment = defensiveEquipment;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setHealthPoint(int hp) {
        this.healthPoint = hp;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void setStrength(int ap) {
        this.attackPoint = ap;
    }

    public OffensiveEquipment getOffensiveEquipment() {
        return offensiveEquipment;
    }

    public void setOffensiveEquipment(OffensiveEquipment offensiveEquipment) {
        this.offensiveEquipment = offensiveEquipment;
    }


    @Override
    public String toString() {
        return "Character{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", health=" + healthPoint +
                ", strength=" + attackPoint +
                ", offensiveEquipment=" + offensiveEquipment +
                '}';
    }
}
