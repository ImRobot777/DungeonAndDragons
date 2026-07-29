package fr.campus.guitarian.dungeoncrawler.characters.types;

import fr.campus.guitarian.dungeoncrawler.characters.Character;
import fr.campus.guitarian.dungeoncrawler.items.OffensiveEquipment;
import fr.campus.guitarian.dungeoncrawler.items.offensive.Weapon;

/**
 * The type Warrior.
 */
public class Warrior extends Character {

    /**
     * Instantiates a new Warrior.
     *
     * @param name the name
     */
    public Warrior(String name){
        super(name, 10, 5, null, null, 10);
    }

    @Override
    public boolean canEquip(OffensiveEquipment equipment) {
        return equipment instanceof Weapon;
    }

}