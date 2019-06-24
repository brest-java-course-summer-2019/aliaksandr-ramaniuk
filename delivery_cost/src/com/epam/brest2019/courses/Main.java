package com.epam.brest2019.courses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Calculate calculate = new Calculate();
        Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the weight in kilograms or 'q' for quit:"); //Введите вес в килограммах или 'q' для выхода:
            String inputString = scanner.nextLine();
            calculate.setWieght(inputString);
            if(calculate.isFlag()) return;

            System.out.println("Enter the distance in kilometers or 'q' for quit: "); //Введите расстояние в километрах или 'q' для выхода:
            inputString = scanner.nextLine();
            calculate.setDistance(inputString);
            if(calculate.isFlag()) return;

        try {
        System.out.println("Value of weight = " + calculate.getWeight() + ". Cost per 1 kilogram = " + calculate.getPricePerKg());
        System.out.println("Value of distance = " + calculate.getDistance() + ". Cost per 1 kilometer = " + calculate.getPricePerKm());
        System.out.println("Total Price = " + calculate.getTotalPrice());

        }
        catch (NullPointerException e){System.out.println(">> Error!"); }
        scanner.close();
    }
}

class Calculate{
    boolean flag = false;
    private BigDecimal weight;
    private BigDecimal distance;
    private BigDecimal pricePerKm;
    private BigDecimal pricePerKg;
    private double distanceDouble;
    String notFile = ">> Price file not found!";
    String invalidInput = ">> Error!\n>> Invalid input!";
    String bye = "Bye!";

    public void setWieght(String inputString) {
        if (!inputString.toLowerCase().equals("q")) {
            try {
                double inputStringKg = Double.parseDouble(inputString);
                if (inputStringKg < 0)  throw new NumberFormatException();
                weight = new BigDecimal(inputString);
            }
            catch (NumberFormatException e){
                System.out.println(invalidInput);
                flag = true;}
                        } else {
                System.out.println(bye);
                flag = true;
        }
    }

    public void setDistance(String inputString) {
        if (!inputString.toLowerCase().equals("q")) {
            try {
                double inputStringKg = Double.parseDouble(inputString);
                if (inputStringKg < 0)  throw new NumberFormatException();
                distance = new BigDecimal(inputString);
                distanceDouble = Double.parseDouble(inputString);
            }
            catch (NumberFormatException e){
                System.out.println(invalidInput);
                flag = true;}
        } else {
            System.out.println(bye);
            flag = true;
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getDistance() { return distance; }

    public BigDecimal getPricePerKg() throws IOException {
          Scanner scanner = null;

        try {
            scanner = new Scanner(new File("/home/alex/development/aliaksandr-ramaniuk/delivery_cost/resources/price_per_kg.properties"));

            while (scanner.hasNextDouble()){
            pricePerKg = new BigDecimal(scanner.nextDouble());
            }
        }

        catch (FileNotFoundException e){
            System.out.println(notFile);
        }

        scanner.close();
        return pricePerKg;
    }

    public BigDecimal getPricePerKm() throws IOException {
        Scanner scanner = null;
        Double [] arr = new Double[3];

        try {
            scanner = new Scanner(new File("/home/alex/development/aliaksandr-ramaniuk/delivery_cost/resources/price_per_km.properties"));
        }

        catch (FileNotFoundException e){
        System.out.println(notFile);
        }

        for (int i = 0; i <arr.length; i++){
            arr[i] = scanner.nextDouble();
        }

        if (distanceDouble <= 10) pricePerKm = new BigDecimal(arr[0]);
        if (distanceDouble > 10 && distanceDouble <=100) pricePerKm = new BigDecimal(arr[1]);
        if (distanceDouble > 100) pricePerKm = new BigDecimal(arr[2]);

        scanner.close();
        return pricePerKm;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
        return totalPrice;
    }

}
