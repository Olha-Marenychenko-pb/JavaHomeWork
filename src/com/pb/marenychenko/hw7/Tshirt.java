package com.pb.marenychenko.hw7;

public class Tshirt extends Clothes implements ManClothes,WomenClothes {
    static String description = "Футболка";
    public Tshirt(Size clothesSize, String color, String cost) {
        super(clothesSize, color, cost);
    }

    @Override
    public void dressMan () {
        System.out.println(this.description + " " + getClothesSize() + " " + getColor() + " " + getCost()
                + " подходит для мужчины");
    };
    public void dressWomen() {
        System.out.println(this.description + " " + getClothesSize() + " " + getColor() + " " + getCost()
                + " подходит для женщины");
    };
}
