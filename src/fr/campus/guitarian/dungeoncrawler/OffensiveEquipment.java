package fr.campus.guitarian.dungeoncrawler;

public abstract class OffensiveEquipment {

    private String name;
    private int attackLevel;

    protected OffensiveEquipment(String name, int attackLevel) {
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

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", attackLevel=" + attackLevel +
                '}';
    }
}
