package fr.campus.guitarian.dungeoncrawler.items.defensive;

import fr.campus.guitarian.dungeoncrawler.items.DefensiveEquipment;

/**
 * The type Shield.
 */
public class Shield extends DefensiveEquipment {

    /**
     * Instantiates a new Shield.
     *
     * @param name         the name
     * @param defenseLevel the defense level
     */
    public Shield(String name, int defenseLevel){
        super(name, defenseLevel);
    }

    /*
    @Override
    public String toString() {
        return "Shield{" +
                "name='" + this.getName() + '\'' +
                ", defenseLevel=" + this.getDefenseLevel() +
                '}';
    }*/
}
