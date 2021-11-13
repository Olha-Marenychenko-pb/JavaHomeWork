package com.pb.marenychenko.hw7;

public abstract class  Clothes {
private Size clothesSize;
private String color;
private String cost;
    public Clothes(Size clothesSize,String color, String cost){
        this.clothesSize = clothesSize;
        this.color = color;
        this.cost = cost;
    }
    public Size getClothesSize() {
        return clothesSize;
    }
    public String getEuroClothesSize() {
        return "(" + clothesSize.getEuroSize() + " " +clothesSize.getDescription() + ")";
    }
    public String getColor() {
        return color;
    }
    public String getCost() {
        return cost;
    }
}
