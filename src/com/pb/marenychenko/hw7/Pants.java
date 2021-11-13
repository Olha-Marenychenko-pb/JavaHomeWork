package com.pb.marenychenko.hw7;

public class Pants extends Clothes implements ManClothes,WomenClothes {
    static String description = "Штаны";
    public Pants(Size clothesSize, String color, String cost) {
        super(clothesSize, color, cost);
    }

    @Override
    public void dressMan () {
        System.out.println(this.description + " " + getClothesSize() + getEuroClothesSize()
                + " " + getColor() + " " + getCost() + " подходят для мужчины");
    };
    public void dressWomen() {
        System.out.println(this.description + " " + getClothesSize() + getEuroClothesSize()
                + " " + getColor() + " " + getCost() + " подходят для женщины");
    };
}