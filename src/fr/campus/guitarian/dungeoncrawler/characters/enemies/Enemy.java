package fr.campus.guitarian.dungeoncrawler.characters.enemies;
import fr.campus.guitarian.dungeoncrawler.characters.Character;

/**
 * The type Enemy.
 */
public abstract class Enemy extends Character {

    /**
     * Instantiates a new Enemy.
     *
     * @param name the name
     * @param hp   the hp
     * @param ap   the ap
     */
    protected Enemy(String name, int hp, int ap){
        super(name, hp, ap, null, null, null);
    }
}
