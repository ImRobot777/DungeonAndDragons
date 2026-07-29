package fr.campus.guitarian.dungeoncrawler.items.offensive;

import fr.campus.guitarian.dungeoncrawler.items.OffensiveEquipment;

/**
 * The type Spell.
 */
public class Spell extends OffensiveEquipment {

    /**
     * Instantiates a new Spell.
     *
     * @param name        the name
     * @param attackLevel the attack level
     */
    public Spell(String name, int attackLevel)
    {
        super(name, attackLevel);
    }

    /*
    @Override
    public String toString() {
        return "Spell{" +
                "name='" + this.getName() + '\'' +
                ", attackLevel=" + this.getAttackLevel() +
                '}';
    }*/

}
