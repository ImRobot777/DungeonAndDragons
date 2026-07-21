package fr.campus.guitarian.dungeoncrawler;

public class DefensiveEquipment {

    private String type="SHIELD";
    private String name="Legendary Shield";
    private int defenseLevel = 1;

    public DefensiveEquipment(String type, String name, int defenseLevel) {
        this.type = type;
        this.name = name;
        this.defenseLevel = defenseLevel;
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

    public int getDefenseLevel() {
        return defenseLevel;
    }

    public void setDefenseLevel(int defenseLevel) {
        this.defenseLevel = defenseLevel;
    }

    @Override
    public String toString() {
        return "DefensiveEquipment{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", defenseLevel=" + defenseLevel +
                '}';
    }
}
