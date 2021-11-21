package com.pb.marenychenko.hw9;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileNumbers {


    public static void print2DArray(int[][] numArray ){
        String outputString = "";
        String delimiter = " ";
        for(int i=0;i<numArray.length;i++){
            for (int j=0;j<numArray[i].length;j++) {
                if (j == 0) {
                    outputString = outputString + numArray[i][j];
                } else {
                    outputString = outputString + delimiter + numArray[i][j];
                }

            }
            if (i < (numArray.length - 1)){
                outputString = outputString + "\n";
            }
        }
        System.out.println(outputString);
    }

    public static int ChangeOddto0(int[][] numArray ){
        int arrayNumber;
        int changed = 0;
        for(int i=0;i<numArray.length;i++) {
            for (int j = 0; j < numArray[i].length; j++) {
                arrayNumber = numArray[i][j];
                if (arrayNumber % 2 == 0)
                {
                    numArray[i][j] = 0;
                    changed++;
                }
            }
        }
      return changed;
    }
    public static int[][] CreateRandom2DArray(int dimensionX, int dimensionY){
        Random r = new Random();
        int low = 1;
        int high = 99;
        int [][] numArray = new int[dimensionX][dimensionY];
        for (int i=0; i<dimensionX; i++ ){
            for (int j=0; j<dimensionY; j++ ){
                numArray[i][j] = r.nextInt(high-low) + low;
            }
        }
        return numArray;
    }

    public static void createOddNumbersFile(){
        String lineString = "";
        int dimesionX = 0;
        int dimensionY = 0;
        int [][] arrayFromFile;
        String [] arrStr;
        Path path = Paths.get("numbers.txt");
        try {
            List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            dimesionX = fileLines.size();

            if (dimesionX > 0)
            {
                lineString = fileLines.get(0);
                dimensionY = lineString.split(" ").length;
                arrayFromFile = new int[dimesionX][dimensionY];
                for(int i=0; i<dimesionX;i++)
                {
                    arrStr = fileLines.get(i).split(" ");
                    for(int j=0; j<dimensionY;j++){
                        arrayFromFile[i][j] = Integer.parseInt(arrStr[j]);
                    }
                }
                System.out.println("Файл: " + path.toAbsolutePath() + " содержит массив (" + dimesionX +"X"+ dimensionY + ")");

                int changed = ChangeOddto0(arrayFromFile);
                System.out.println("Заменяем четные чила в массиве...(количество замен:" + changed + ")");
                array2DtoFile("odd-numbers.txt", arrayFromFile);
                print2DArray(arrayFromFile);
            }
            else
                {
                    System.out.println("Файл: " + path.toAbsolutePath() + " не содержит 2D массив");
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void array2DtoFile(String fileName,  int [][] numArray)
    {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            String outputString = "";
            String delimiter = " ";
            for(int i=0;i<numArray.length;i++){
                for (int j=0;j<numArray[i].length;j++) {
                    if (j == 0) {
                        outputString = outputString + numArray[i][j];
                    } else {
                        outputString = outputString + delimiter + numArray[i][j];
                    }

                }
                writer.write(outputString);
                outputString = "";
                if(i < (numArray.length - 1)){
                    writer.newLine();}
            }

            System.out.println("Записываем массив в файл: " + path.toAbsolutePath());

        } catch (Exception ex) {
            System.out.println("Error with file write: " + ex);
        }
    }
    public static void createNumbersFile(){

        System.out.println("Создаем массив (10x10) случайных чисел:");
        int [][] numArray = CreateRandom2DArray(10,10);
        array2DtoFile("numbers.txt", numArray);
        print2DArray(numArray);
    }

    public static void main(String[] args) {
        createNumbersFile();
        createOddNumbersFile();
    }
}
