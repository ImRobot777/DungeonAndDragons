package fr.campus.guitarian.dungeoncrawler.items;

public abstract class DefensiveEquipment {

    private String name;
    private int defenseLevel;

    protected DefensiveEquipment(String name, int defenseLevel) {
        this.name = name;
        this.defenseLevel = defenseLevel;
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
        return  this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", defenseLevel=" + defenseLevel +
                '}';
    }
}
