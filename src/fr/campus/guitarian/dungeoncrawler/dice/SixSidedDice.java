package fr.campus.guitarian.dungeoncrawler.dice;

import java.util.Random;

public class SixSidedDice implements Dice {
    Random random;

    public SixSidedDice(Random random){
        this.random = random;
    }

    @Override
    public int roll() {
        return this.random.nextInt(6) + 1;
    }
}
