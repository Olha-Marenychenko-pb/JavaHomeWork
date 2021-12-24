package com.pb.marenychenko.hw13;

public enum GameResult {
    VICTORY("Победа"),
    DEFEAT("Поражение"),
    DRAW("Ничья");
    private String desc;

    GameResult(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }
}
