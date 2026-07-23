package fr.campus.guitarian.dungeoncrawler;

public class Shield extends DefensiveEquipment {

    public Shield(String name, int defenseLevel){
        super(name, defenseLevel);
    }


    @Override
    public String toString() {
        return "Shield{" +
                "name='" + this.getName() + '\'' +
                ", defenseLevel=" + this.getDefenseLevel() +
                '}';
    }
}
