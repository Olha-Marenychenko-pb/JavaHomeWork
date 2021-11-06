package com.pb.marenychenko.hw6;

import com.Testirovka.Computer;

public class Veterinarian {
    private String doctorFIO;
    private String professionalSkills;
    public Veterinarian(String fio, String skills)
    {
        this.doctorFIO = fio;
        this.professionalSkills = skills;
    }
    public String getDoctorFio(){ return doctorFIO; }
    public String getDoctorSkills(){ return professionalSkills; }
    public void setDoctorSkills(String skills){ this.professionalSkills = skills; }

    void treatAnimal(Animal animal)
    {   String nickname = "";
        if (animal instanceof Dog)
            {
                nickname = ((Dog)animal).getNickName();
            }
        else if (animal instanceof Cat)
        {
            nickname = ((Cat)animal).getNickName();
        }
        else if (animal instanceof Horse)
        {
            nickname = ((Horse)animal).getNickName();
        }
        if (!nickname.isEmpty()){
            nickname =  " по кличке " + nickname;
        }
        System.out.println( getDoctorFio() + " осматривает " + animal.getName() + nickname + " из "
                + animal.getLocation() + " что питалось " + animal.getFood() );
    }

}
