package fr.campus.guitarian.dungeoncrawler.characters.types;

import fr.campus.guitarian.dungeoncrawler.characters.Character;

public class Wizard extends Character {

    public Wizard(String name)
    {
        super(name, 7, 7, null, null);

    }

    /*
    @Override
    public String toString() {
        return "Wizard{" +
                "name='" + this.getName() + '\'' +
                ", healthPoints=" + this.getHealthPoint() +
                ", attackLevel=" + this.getAttackPoint() +
                ", offensiveEquipment=" + getOffensiveEquipment() +
                ", defensiveEquipment=" + getDefensiveEquipment() +
                '}';
    }*/
}
