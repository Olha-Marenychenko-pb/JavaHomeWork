package com.pb.marenychenko.hw13;

import java.util.Random;

public class Player extends Thread{
    private int rounds;
    private GameBox playerBox;
    private int playerSlow;
    private String color;
    public static final String ANSI_RESET = "\u001B[0m";


    public Player(String color, int playerSlow, GameBox playerBox, int rounds){
    this.rounds = rounds;
    this.playerBox = playerBox;
    this.playerSlow = playerSlow;
    this.color = color;

    };

    @Override
    public void run() {
        int thinkTime;
        boolean putInBox;
        RockPaperScissors playerChoice;

        for (int i=1;i<=rounds;i++){
            thinkTime = new Random().nextInt(playerSlow) +1;
            System.out.println(color + "#Раунд: " + i + " #. " + Thread.currentThread().getName()
                    + ": Задумался над выбором на " + thinkTime + " секунд" + ANSI_RESET);
            try {
                Thread.currentThread().sleep(thinkTime* 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playerChoice = RockPaperScissors.randomChoice();
            System.out.println(color + "#Раунд: " + i + " #. " + Thread.currentThread().getName()
                    + ": Сделал выбор" +ANSI_RESET);
            do{
                System.out.println(color + "#Раунд: " + i + " #. " + Thread.currentThread().getName()
                        + ": пытается положить листок в свою коробку.." +ANSI_RESET);
            putInBox = playerBox.addPlayerChoice(playerChoice);
            if (!putInBox)
            {
                thinkTime = new Random().nextInt(playerSlow/2) +1;
                System.out.println(color + "#Раунд: " + i + " #. " + Thread.currentThread().getName()
                        + ": Игровая коробка переполнена. Пойду перекурю  на " + thinkTime +" сек" + ANSI_RESET);
                try {
                    Thread.currentThread().sleep(thinkTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            }while(!putInBox);
            System.out.println(color + "#Раунд: " + i + " #. " + Thread.currentThread().getName()
                    + ": положил листок в свою коробку" +ANSI_RESET);

        }
    }
}
