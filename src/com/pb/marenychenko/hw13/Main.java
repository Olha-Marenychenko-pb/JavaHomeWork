package com.pb.marenychenko.hw13;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        System.out.println("*** Игра Камень-Ножницы-Бумага ***");
        System.out.println("");
        System.out.println("Описание игры:");
        System.out.println("Два игрока Игрок1 и Игрок2 играют в Камень-Ножницы-Бумага 10 раундов");
        System.out.println("Участники записывают свой игровой выбор на листок и ложат в свою коробку, что вмещаает 5 листков");
        System.out.println("Судья достает листки с коробок игроков, сравнивает и объявляет результат");
        System.out.println("Засчитываются только победы");
        System.out.println("Если коробка переполнена, то Игрок идет на перекур, а если пуста, то Судья идет пить кофе");
        System.out.println("*** Подготовка участников к игре ***");
        System.out.println("");

        int rounds = 10;
        int gameBoxSize = 5;

        String player1Name = "Игрок1";
        int player1Slow = 4;
        GameBox player1Box = new GameBox(gameBoxSize,player1Name);
        String ANSI_RED = "\u001B[31m";
        Player player1 = new Player(ANSI_RED, player1Slow, player1Box, rounds);
        player1.setName(player1Name);

        String player2Name = "Игрок2";
        int player2Slow = 8;
        GameBox player2Box = new GameBox(gameBoxSize,player2Name);
        String ANSI_BLUE = "\u001B[34m";
        Player player2 = new Player(ANSI_BLUE, player2Slow, player2Box, rounds);
        player2.setName(player2Name);

        int judgeSlow = 5;
        String ANSI_GREEN = "\u001B[32m";
        Judge judge = new Judge(ANSI_GREEN, judgeSlow,player1Box, player2Box, rounds);
        System.out.println("*** Игра началась!!! ***");
        System.out.println("");

        player1.start();
        player2.start();
        judge.run();

        judge.join();
        System.out.println("");
        System.out.println("*** Игра Окончена ***");
    }
}
