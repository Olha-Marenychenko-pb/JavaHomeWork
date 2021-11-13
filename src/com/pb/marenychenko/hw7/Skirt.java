package com.pb.marenychenko.hw7;

public class Skirt extends Clothes implements WomenClothes {
    static String description = "Юбка";
    public Skirt(Size clothesSize, String color, String cost) {
        super(clothesSize, color, cost);
    }

    public void dressWomen() {
        System.out.println(this.description + " " + getClothesSize() + getEuroClothesSize()
                + " " + getColor() + " " + getCost() + " подходит для женщины");
    };
}