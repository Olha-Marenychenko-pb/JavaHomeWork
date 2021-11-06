package com.pb.marenychenko.hw6;

public class Animal {
    private String animalName;
    private String food;
    private String location;

    public Animal() {
        animalName = "Зверь";
        food = "Корм";
        location = "Среда обитания";
    }

    public Animal(String name, String food, String location) {
        this.animalName = name;
        this.food = food;
        this.location = location;
    }
    public String getName() {
        return animalName;
    }
    public void setName(String name) {
        this.animalName = name;
    }
    public String getFood() {
        return food;
    }
    public void setFood(String food) {
        this.food = food;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public void sleep(){
        System.out.println(animalName + " спит");
    }
    public void makeNoise(){
        System.out.println(animalName + " издает обычные звуки");
    }
    public void eat(){
        System.out.println(animalName + " кушает");
    }
}
