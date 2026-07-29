package fr.campus.guitarian.dungeoncrawler.characters.types;

import fr.campus.guitarian.dungeoncrawler.characters.Character;
import fr.campus.guitarian.dungeoncrawler.items.OffensiveEquipment;
import fr.campus.guitarian.dungeoncrawler.items.offensive.Spell;

/**
 * The type Wizard.
 */
public class Wizard extends Character {

    /**
     * Instantiates a new Wizard.
     *
     * @param name the name
     */
    public Wizard(String name)
    {
        super(name, 7, 7, null, null, 7);
    }

    @Override
    public boolean canEquip(OffensiveEquipment equipment) {
        return equipment instanceof Spell;
    }

}