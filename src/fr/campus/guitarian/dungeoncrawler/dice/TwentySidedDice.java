package fr.campus.guitarian.dungeoncrawler.dice;

import java.util.Random;

public class TwentySidedDice implements Dice {

    Random random;

    public TwentySidedDice(Random random) {
        this.random = random;
    }

    @Override
    public int roll() {
        return this.random.nextInt(20) + 1;
    }
}
