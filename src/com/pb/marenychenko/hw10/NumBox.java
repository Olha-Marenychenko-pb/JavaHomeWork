package com.pb.marenychenko.hw10;

import java.sql.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class NumBox <T extends Number>{
    private List <T> numBoxArray;
    private int maxSize;
    public NumBox(int size){
        maxSize = size;
        numBoxArray = new ArrayList<>(size);
    }
    public void add(T num)throws ArrayIndexOutOfBoundsException{
        if (this.length() < maxSize){
            numBoxArray.add(num);
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Массив уже достиг максимального размера " + maxSize);
        }
    }
    public int length()
    {
        return numBoxArray.size();
    }
    public double sum() throws IllegalStateException{
        if (this.length()==0)
            throw new IllegalStateException("Массив пуст. Операция невозможна");
        double sum = 0;
        for(T d : numBoxArray){
            sum += d.doubleValue();
        }
        return sum;
    }
    public double average(){
        return (this.sum())/(this.length());
    }
    public T max() throws IllegalStateException{
        if (this.length()==0)
            throw new IllegalStateException("Массив пуст. Операция невозможна");
        T max = numBoxArray.get(0);
        for(T d : numBoxArray){
            if (max.doubleValue() < d.doubleValue()) {
                max = d;
            }
        }
        return max;
    }
    public T get(int index){
        if ((index>=0) && (index<this.length()))
        {
            return numBoxArray.get(index);
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Обращение к неинициализированному элементу массива");
        }

    }
    public String toString()
    { String returnString="";
        if (this.length()==0)
        {
            return "Пустой массив";
        }
        for(T d : numBoxArray){
            returnString = returnString + " " + d.toString();
        }
        return returnString.substring(1);
    }
}

