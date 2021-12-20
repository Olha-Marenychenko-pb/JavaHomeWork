package com.pb.marenychenko.hw12;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Person implements Serializable {

    private final static long serialVersionUID = 42;

    private String fio;
    private String address;
    LinkedHashSet<String> phones = new LinkedHashSet<>();

    private LocalDate dateOfBirth;
    private LocalDateTime recordChangeTime;

    public Person() {
    }

    public Person(String fio, LinkedHashSet<String>phones) {
        this.fio = fio;
        this.phones = phones;
        setRecordChangeTime();
    }

    public Person(String fio, LinkedHashSet<String>phones, LocalDate dateOfBirth) {
        this.fio = fio;
        this.phones = phones;
        this.dateOfBirth = dateOfBirth;
        setRecordChangeTime();
    }
    public Person(String fio, LinkedHashSet<String>phones, String address) {
        this.fio = fio;
        this.phones = phones;
        this.address = address;
        setRecordChangeTime();
    }

    public Person(String fio, LinkedHashSet<String>phones, LocalDate dateOfBirth, String address) {
        this.fio = fio;
        this.phones = phones;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        setRecordChangeTime();
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
        setRecordChangeTime();
    }

    public LocalDateTime getRecordChangeTime() {
        return recordChangeTime;
    }
    public void setRecordChangeTime() {
        this.recordChangeTime = LocalDateTime.now();
    }

    public void setPhones(LinkedHashSet<String> phones) {
        this.phones = phones;
        setRecordChangeTime();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LinkedHashSet<String> getPhones() {
        return phones;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        setRecordChangeTime();
    }

    @Override
    public String toString() {
        String retStr = "-----------------------------------------\n";
        retStr = retStr + "ФИО: '" + fio + '\'' +'\n';
        if ((phones != null) && (phones.size() > 0))
            retStr = retStr + "Телефоны: '" + getPhones() + '\'' + '\n';
        if (Objects.nonNull(dateOfBirth))
            retStr = retStr + "Дата рождения: " + dateOfBirth + '\n';
        if ((address != null) && (address.length() > 0))
            retStr = retStr + "Адрес: '" + address + '\'' + '\n';
        retStr = retStr + "Запись Изменена: " + getRecordChangeTime() + '\n';
        retStr = retStr + "-----------------------------------------\n";
    return retStr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fio, dateOfBirth);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return hashCode() == person.hashCode();
    }
}
