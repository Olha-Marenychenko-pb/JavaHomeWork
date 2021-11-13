package com.pb.marenychenko.hw7;

public class Atelier {
    public static void main(String[] args) {
        Tshirt tshirt = new Tshirt(Size.XS, "blue", "434uah");
        Skirt skirt = new Skirt(Size.M, "red", "300usd");
        Skirt skirt2 = new Skirt(Size.S, "brown", "600uah");
        Pants pants = new Pants(Size.L, "black", "500eur");
        Pants pants2 = new Pants(Size.XXS, "orange", "330usd");
        Tie tie = new Tie(Size.XXS, "yellow", "200uah");
        Tie tie2 = new Tie(Size.XS, "green", "322uah");

        Clothes[] array_clothes = {tshirt, skirt, skirt2, pants, pants2, tie, tie2};
        System.out.println("Одежда что подойдет мужчинам:");
        dressMan(array_clothes);
        System.out.println();
        System.out.println("Одежда что подойдет женщинам:");
        dressWomen(array_clothes);

    }

    public static void dressMan(Clothes[] array_clothes) {
        for (Clothes clothes : array_clothes) {
            if (clothes instanceof ManClothes) {
                ManClothes manClothes = (ManClothes) clothes;
                manClothes.dressMan();
            }
        }
    }
    public static void dressWomen(Clothes[] array_clothes) {
        for (Clothes clothes : array_clothes) {
            if (clothes instanceof WomenClothes) {
                WomenClothes womenClothes = (WomenClothes) clothes;
                womenClothes.dressWomen();
            }
        }
    }

}