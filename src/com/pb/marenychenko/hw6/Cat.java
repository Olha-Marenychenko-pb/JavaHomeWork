package com.pb.marenychenko.hw6;

import java.util.Objects;

public class Cat extends Animal {
    private boolean canClimbTrees;
    private String breed;
    private String nickName;
    public Cat(String nickName, String breed) {
        super("Кот", "Рыба","Дом");
        this.nickName = nickName;
        this.breed = breed;
        canClimbTrees = true;
    }
    public void setCanClimbTrees(boolean climb) {
        this.canClimbTrees = climb;
    }
    public String getNickName(){return nickName;}
    public boolean getCanClimbTrees() {
        return this.canClimbTrees;
    }
    public void makeNoise(){
        System.out.println(getName() + " по кличке " + nickName + " делает тыгыдык");
    }
    public void eat(){
        System.out.println(getName() + " по кличке " + nickName + " кушает " + getFood());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return (Objects.equals( super.getName().toLowerCase(), cat.getName().toLowerCase())) &&
                (Objects.equals(breed.toLowerCase(), cat.breed.toLowerCase())) &&
                (Objects.equals( super.getLocation().toLowerCase(), cat.getLocation().toLowerCase())) &&
                (Objects.equals( super.getFood().toLowerCase(), cat.getFood().toLowerCase())) &&
                (Objects.equals(nickName.toLowerCase(), cat.nickName.toLowerCase())) &&
                (canClimbTrees=cat.getCanClimbTrees());
    }
    @Override
    public String toString() {
        String retSrt = super.getName() + " породы " + breed + " проживает в " + getLocation() + " питается "
                + getFood() + " откликается на " + nickName;
        if (canClimbTrees) {
            retSrt = retSrt + " и лазит по деревьям";
        }
        return retSrt;

    }
    @Override
    public int hashCode() {
        return Objects.hash(super.getName().toLowerCase(), breed.toLowerCase(), super.getLocation().toLowerCase(),
                super.getFood().toLowerCase(), nickName.toLowerCase(), canClimbTrees);
    }
}
