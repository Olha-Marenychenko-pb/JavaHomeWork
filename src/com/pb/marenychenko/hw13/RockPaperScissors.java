package com.pb.marenychenko.hw13;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum RockPaperScissors {
    ROCK("Камень"),
    PAPER("Бумага"),
    SCISSORS("Ножницы");
    private String desc;

    RockPaperScissors(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

    private static final List<RockPaperScissors> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static RockPaperScissors randomChoice() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
    public GameResult compareChoices(RockPaperScissors otherChoice) {
        // Ничья
        if (this == otherChoice)
            return GameResult.DRAW;

        switch (this) {
            case ROCK:
                return (otherChoice == SCISSORS ? GameResult.VICTORY : GameResult.DEFEAT);
            case PAPER:
                return (otherChoice == ROCK ? GameResult.VICTORY : GameResult.DEFEAT);
            case SCISSORS:
                return (otherChoice == PAPER ? GameResult.VICTORY : GameResult.DEFEAT);
        }
        return GameResult.DRAW;
    };
}
