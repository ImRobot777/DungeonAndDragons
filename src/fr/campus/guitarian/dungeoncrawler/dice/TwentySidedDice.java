package fr.campus.guitarian.dungeoncrawler.dice;

import java.util.Random;

/**
 * The type Twenty sided dice.
 */
public class TwentySidedDice implements Dice {

    /**
     * The Random.
     */
    Random random;

    /**
     * Instantiates a new Twenty sided dice.
     *
     * @param random the random
     */
    public TwentySidedDice(Random random) {
        this.random = random;
    }

    @Override
    public int roll() {
        return this.random.nextInt(20) + 1;
    }
}
