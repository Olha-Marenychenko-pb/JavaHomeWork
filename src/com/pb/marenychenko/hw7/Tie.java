package com.pb.marenychenko.hw7;

public class Tie extends Clothes implements ManClothes {
    static String description = "Галстук";
    public Tie(Size clothesSize, String color, String cost) {
        super(clothesSize, color, cost);
    }

    @Override
    public void dressMan () {
        System.out.println(this.description + " " + getClothesSize() + " " + getColor() + " " + getCost()
                + " подходит для мужчины");
    };

}