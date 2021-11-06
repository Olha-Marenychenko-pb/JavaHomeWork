package com.pb.marenychenko.hw6;

import java.util.Objects;

public class Horse extends Animal{
    private boolean isRaceHorse;
    private String breed;
    private String nickName;
    public Horse(String nickName, String breed) {
        super("Конь", "Овес","Ранчо");
        this.nickName = nickName;
        this.breed = breed;
        isRaceHorse = false;
    }
    public void setIsRaceHorse(boolean isRaceHorse) {
        this.isRaceHorse = isRaceHorse;
    }
    public String getNickName(){return nickName;}

    public boolean getIsRaceHorse() {
        return this.isRaceHorse;
    }

    public void makeNoise(){
        System.out.println(getName() + " по кличке " + nickName + " ржет");
    }
    public void eat(){
        System.out.println(getName() + " по кличке " + nickName + " кушает " + getFood() +
                " и бьет копытцем");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return (Objects.equals( super.getName().toLowerCase(), horse.getName().toLowerCase())) &&
                (Objects.equals(breed.toLowerCase(), horse.breed.toLowerCase())) &&
                (Objects.equals( super.getLocation().toLowerCase(), horse.getLocation().toLowerCase())) &&
                (Objects.equals( super.getFood().toLowerCase(), horse.getFood().toLowerCase())) &&
                (Objects.equals(nickName.toLowerCase(), horse.nickName.toLowerCase())) &&
                (isRaceHorse=horse.getIsRaceHorse());
    }
    @Override
    public String toString() {
        String retSrt = super.getName() + " породы " + breed + " проживает в " + getLocation() + " питается "
                + getFood() + " откликается на " + nickName;
        if (isRaceHorse) {
            retSrt = retSrt + " и готова  победить в гонке";
        }
        return retSrt;

    }
    @Override
    public int hashCode() {
        return Objects.hash(super.getName().toLowerCase(), breed.toLowerCase(), super.getLocation().toLowerCase(),
                super.getFood().toLowerCase(), nickName.toLowerCase(), isRaceHorse);
    }

}
