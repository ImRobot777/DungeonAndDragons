package fr.campus.guitarian.dungeoncrawler.db;

public class CharacterRow {
    private int id;
    private String type;
    private String name;
    private int lifePoints;
    private int attackPoints;
    private String offensiveEquipment;
    private String defensiveEquipment;

    public CharacterRow(int id, String type, String name, int lifePoints, int attackPoints, String offensiveEquipment, String defensiveEquipment) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.lifePoints = lifePoints;
        this.attackPoints = attackPoints;
        this.offensiveEquipment = offensiveEquipment;
        this.defensiveEquipment = defensiveEquipment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public String getOffensiveEquipment() {
        return offensiveEquipment;
    }

    public void setOffensiveEquipment(String offensiveEquipment) {
        this.offensiveEquipment = offensiveEquipment;
    }

    public String getDefensiveEquipment() {
        return defensiveEquipment;
    }

    public void setDefensiveEquipment(String defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }
}
