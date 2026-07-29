package fr.campus.guitarian.dungeoncrawler.items.offensive;

import fr.campus.guitarian.dungeoncrawler.items.OffensiveEquipment;

/**
 * The type Weapon.
 */
public class Weapon extends OffensiveEquipment {

    /**
     * Instantiates a new Weapon.
     *
     * @param name        the name
     * @param attackLevel the attack level
     */
    public Weapon(String name, int attackLevel)
    {
        super(name, attackLevel);
    }

    /*
    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + this.getName() + '\'' +
                ", attackLevel=" + this.getAttackLevel() +
                '}';
    }
     */

}
