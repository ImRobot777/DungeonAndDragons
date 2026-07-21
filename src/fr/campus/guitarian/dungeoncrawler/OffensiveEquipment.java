package fr.campus.guitarian.dungeoncrawler;

public class OffensiveEquipment {
    private String type="SWORD";
    private String name="Legendary Sword";
    private int attackLevel = 1;

    public OffensiveEquipment(String type, String name, int attackLevel) {
        this.type = type;
        this.name = name;
        this.attackLevel = attackLevel;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OffensiveEquipment{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", attackLevel=" + attackLevel +
                '}';
    }
}
