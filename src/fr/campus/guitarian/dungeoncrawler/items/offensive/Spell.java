package fr.campus.guitarian.dungeoncrawler.items.offensive;

import fr.campus.guitarian.dungeoncrawler.items.OffensiveEquipment;

public class Spell extends OffensiveEquipment {

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
