package fr.campus.guitarian.dungeoncrawler.characters.enemies;
import fr.campus.guitarian.dungeoncrawler.characters.Character;

public abstract class Enemy extends Character {

    protected Enemy(String name, int hp, int ap){
        super(name, hp, ap, null, null, null);
    }
}
