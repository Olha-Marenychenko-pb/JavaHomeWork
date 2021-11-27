package com.pb.marenychenko.hw10;

public class Main {
    public static void main(String[] args) {
        //Создаем массивы

        NumBox<Float> numBox1 =new NumBox(3);
        NumBox<Integer> numBox2 =new NumBox(6);

        //Создаем числа
        Float float1 = new Float(1.7);
        Float float2 = new Float(-21.5);
        Float float3 = new Float(8.2);
        Float float4 = new Float(34.32);


        Integer int1 = new Integer(7);
        Integer int2 = new Integer(-9);
        Integer int3 = new Integer(54);
        Integer int4 = new Integer(12);


        try {
            //numBox1.sum();
            //numBox2.average();
            //numBox2.max();
            //Exception:Массив пуст. Операция невозможна

            //Наполняем массивы числами
            numBox1.add(float1);
            numBox1.add(float2);
            numBox1.add(float3);
            System.out.println("Массив с Float цифрами: " + numBox1.toString());
            System.out.println("Размер массива с Float: " + numBox1.length());

            //numBox1.add(float4);
            //Exception: Массив уже достиг максимального размера 3

            numBox2.add(int1);
            numBox2.add(int2);
            numBox2.add(int3);
            numBox2.add(int4);
            System.out.println("Массив с Integer цифрами: " + numBox2.toString());
            System.out.println("Размер массива с Integer: " + numBox2.length());

            System.out.println("Элемнт массива Float с индексом 2: " + numBox1.get(2));
            System.out.println("Элемнт массива Integer с индексом 1: " + numBox2.get(1));

            //System.out.println(numBox2.get(5));
            //Exception: Обращение к неинициализированному элементу массива

            System.out.println("Cумма элементов массива с Integer: " + numBox2.sum());
            System.out.println("Среднее арифметическое элементов массива с Float: " + numBox1.average());

            System.out.println("Максимальный элемент в массиве с Integer: " + numBox2.max());
            System.out.println("Максимальный элемент в массиве с Float: " + numBox1.max());


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
