package com.pb.marenychenko.hw13;

import java.util.ArrayList;

public class GameBox {
    private final int gameBoxSize;
    private final String playerName;
    private final ArrayList<RockPaperScissors> playerBox;

    public GameBox(int gameBoxSize,String playerName){
        this.gameBoxSize = gameBoxSize;
        this.playerName = playerName;
        this.playerBox = new ArrayList<RockPaperScissors>();
    }

    public final RockPaperScissors getPlayerChoice() throws IndexOutOfBoundsException {
        synchronized (playerBox) {
            RockPaperScissors operationResult;

            if (playerBox.size() > 0) {
                operationResult = playerBox.remove(0);
            } else throw new IndexOutOfBoundsException("Игровой ящик пуст");

            return operationResult;
        }
    }

    public final boolean addPlayerChoice(RockPaperScissors playerChoice)
    {
        synchronized (playerBox)
        {
        boolean operationResult = false;
        if (playerBox.size()<gameBoxSize)
        {
            playerBox.add(playerChoice);
            operationResult = true;
        }
        return operationResult;
        }
    }

    public String getPlayerName() {
        return playerName;
    }
}
