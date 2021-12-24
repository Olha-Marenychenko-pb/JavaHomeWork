package com.pb.marenychenko.hw13;

import java.util.Random;

public class Judge extends Thread{
    private int rounds;
    private int judgeSlow;
    private String color;
    private int player1Victories;
    private int player2Victories;
    public static final String ANSI_RESET = "\u001B[0m";

    private GameBox player1Box;
    private GameBox player2Box;

    private RockPaperScissors getPlayerChoice(GameBox playerBox, int round, String player){
        RockPaperScissors playerChoice = null;
        int thinkTime;
        boolean waitForPlayer = true;
        do {
            try {
                System.out.println( color + "#Раунд: " + round + " #. Судья : пытается достать листок из коробки "
                        + player + ".." + ANSI_RESET);
                playerChoice = playerBox.getPlayerChoice();
                System.out.println( color + "#Раунд: " + round + " #. Судья : достал листок из коробки "
                        + player +  ANSI_RESET);
                waitForPlayer = false;
            } catch (Exception ex) {
                waitForPlayer = true;

                thinkTime = new Random().nextInt(judgeSlow/2) + 1;
                System.out.println( color + "#Раунд: " + round + " #. Судья : " + player +
                        " еще не готов - отойду на кофеек на " + thinkTime + " сек" + ANSI_RESET);
                try {
                    Thread.currentThread().sleep(thinkTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        while (waitForPlayer);
        return playerChoice;
    }

    public Judge(String color, int judgeSlow, GameBox player1Box, GameBox player2Box, int rounds){
        this.rounds = rounds;
        this.player1Box = player1Box;
        this.player2Box = player2Box;
        this.player1Victories = 0;
        this.player2Victories = 0;
        this.judgeSlow = judgeSlow;
        this.color = color;
    };
    @Override
    public void run() {
        int thinkTime;
        RockPaperScissors player1Choice;
        RockPaperScissors player2Choice;
        String player1Name = player1Box.getPlayerName();
        String player2Name = player2Box.getPlayerName();

        for (int i=1;i<=rounds;i++){

            player1Choice= getPlayerChoice(player1Box,i, player1Name);
            player2Choice= getPlayerChoice(player2Box,i, player2Name);

            thinkTime = new Random().nextInt(judgeSlow) +1;
            System.out.println(color + "#Раунд: " + i + " #. Cудья: Задумался над решением "
                    + thinkTime + " секунд" + ANSI_RESET);

            try {
                Thread.currentThread().sleep(thinkTime* 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            GameResult gameResult = player1Choice.compareChoices(player2Choice);

            System.out.println(color + "#Раунд: " + i + " #. Судья: " + player1Name + " поставил: "
                    + player1Choice.getDesc() + ", а " + player2Name +
                    " поставил: " + player2Choice.getDesc() +ANSI_RESET);
            switch (gameResult)
            {
                case  DRAW: System.out.println(color + "#Раунд: " + i + " #. Судья: Мой вердикт: "
                        + gameResult.getDesc() +ANSI_RESET); break;
                case  VICTORY:
                    System.out.println(color + "#Раунд: " + i + " #. Судья: Мой вердикт: "
                            + player1Name + " - " + gameResult.getDesc() + ",  "
                            + player2Name + " - " + GameResult.DEFEAT.getDesc() + ANSI_RESET);
                    player1Victories++;
                    break;
                case  DEFEAT:
                    System.out.println(color + "#Раунд: " + i + " #. Судья: Мой вердикт: "
                            + player1Name + " - " + gameResult.getDesc() + ",  "
                            + player2Name + " - " + GameResult.VICTORY.getDesc() + ANSI_RESET);
                    player2Victories++;
                    break;
            }
        }
        System.out.println();
        System.out.print(color + "Окончательный Вердикт: " + ANSI_RESET);
        if (player1Victories==player2Victories) {
            System.out.println(color + "Счет " + player1Victories +
                    ":" + player2Victories + " - " + GameResult.DRAW.getDesc()  + ANSI_RESET);
        }
        else if (player1Victories>player2Victories){
            System.out.println(color + "Со счетом " + player1Victories +
                    ":" + player2Victories + " - " + GameResult.VICTORY.getDesc() + " " + player1Name + ANSI_RESET);
        }
        else {
            System.out.println(color + "Со счетом " + player2Victories +
                    ":" + player1Victories + " - " + GameResult.VICTORY.getDesc() + " " + player2Name + ANSI_RESET);
        };
    }
}
