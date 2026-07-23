package fr.campus.guitarian.dungeoncrawler;

public class Potion extends DefensiveEquipment {

    public Potion(String name, int defenseLevel){
        super(name, defenseLevel);
    }


    @Override
    public String toString() {
        return "Potion{" +
                "name='" + this.getName() + '\'' +
                ", defenseLevel=" + this.getDefenseLevel() +
                '}';
    }

}
