package com.pb.marenychenko.hw6;

import java.util.Objects;

public class Dog extends Animal {
    private boolean isTrained;
    private String breed;
    private String nickName;
    public Dog(String nickName, String breed) {
        super("Собака", "Мясо","Будка");
        this.nickName = nickName;
        this.breed = breed;
        isTrained = false;
    }
    public void setIsTrained(boolean trained) {
        this.isTrained = trained;
    }
    public String getNickName(){return nickName;}
    public boolean getIsTrained() {
        return this.isTrained;
    }
    public void makeNoise(){
        System.out.println(getName() + " по кличке " + nickName + " гавкает");
    }
    public void eat(){
        System.out.println(getName() + " по кличке " + nickName + " кушает " + getFood() +
                " и грызет тапки");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return (Objects.equals( super.getName().toLowerCase(), dog.getName().toLowerCase())) &&
                (Objects.equals(breed.toLowerCase(), dog.breed.toLowerCase())) &&
                (Objects.equals( super.getLocation().toLowerCase(), dog.getLocation().toLowerCase())) &&
                (Objects.equals( super.getFood().toLowerCase(), dog.getFood().toLowerCase())) &&
                (Objects.equals(nickName.toLowerCase(), dog.nickName.toLowerCase())) &&
                (isTrained=dog.getIsTrained());
    }
    @Override
    public String toString() {
        String retSrt = super.getName() + " породы " + breed + " проживает в " + getLocation() + " питается "
                + getFood() + " откликается на " + nickName;
        if (isTrained) {
            retSrt = retSrt + " и подает лапу";
        }
        return retSrt;

    }
    @Override
    public int hashCode() {
        return Objects.hash(super.getName().toLowerCase(), breed.toLowerCase(), super.getLocation().toLowerCase(),
                super.getFood().toLowerCase(), nickName.toLowerCase(), isTrained);
    }

}
