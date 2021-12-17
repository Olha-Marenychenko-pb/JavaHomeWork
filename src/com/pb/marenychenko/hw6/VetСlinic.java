package com.pb.marenychenko.hw6;

import java.lang.reflect.Constructor;

public class VetСlinic {
    public static void main(String[] args) throws Exception{
        Animal [] animals ={new Dog("Шарик","Двортерьер"),
                new Cat("Персик", "Персидский"),
                new Horse("Снежок", "Крестьянская"),
                //new Animal("Хомяк", "зерно","поле")
        };

        Class docClazz = Class.forName("com.pb.marenychenko.hw6.Veterinarian");
        Constructor constr = docClazz.getConstructor(new Class[] {String.class, String.class});
        Object vetDoctor = constr.newInstance("доктор Айболит", "Лечит всех зверей");
        for (Animal animal: animals){
            ((Veterinarian)vetDoctor).treatAnimal(animal);
        }
    }
}
