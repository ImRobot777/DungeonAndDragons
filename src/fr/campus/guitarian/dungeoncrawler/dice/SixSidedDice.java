package fr.campus.guitarian.dungeoncrawler.dice;

import java.util.Random;

/**
 * The type Six sided dice.
 */
public class SixSidedDice implements Dice {
    /**
     * The Random.
     */
    Random random;

    /**
     * Instantiates a new Six sided dice.
     *
     * @param random the random
     */
    public SixSidedDice(Random random){
        this.random = random;
    }

    @Override
    public int roll() {
        return this.random.nextInt(6) + 1;
    }
}
