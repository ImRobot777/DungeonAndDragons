package fr.campus.guitarian.dungeoncrawler.items.defensive;

import fr.campus.guitarian.dungeoncrawler.items.DefensiveEquipment;

/**
 * The type Potion.
 */
public class Potion extends DefensiveEquipment {

    /**
     * Instantiates a new Potion.
     *
     * @param name         the name
     * @param defenseLevel the defense level
     */
    public Potion(String name, int defenseLevel){
        super(name, defenseLevel);
    }

    /*
    @Override
    public String toString() {
        return "Potion{" +
                "name='" + this.getName() + '\'' +
                ", defenseLevel=" + this.getDefenseLevel() +
                '}';
    }*/

}
