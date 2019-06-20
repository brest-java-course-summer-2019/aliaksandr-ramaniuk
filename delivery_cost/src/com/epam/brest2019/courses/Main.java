package com.epam.brest2019.courses;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BigDecimal weight;
        BigDecimal distance;

        BigDecimal pricePerKg = new BigDecimal("30");
        BigDecimal pricePerKm = new BigDecimal("50");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the weight in kilograms or 'q' for quit: ");
        String inputString = scanner.nextLine();
        if (!inputString.equals("Q")) {
            weight = new BigDecimal(inputString);
        } else {
            System.out.println("\nBye!");
            return;
        }

        System.out.println("Enter the distance in kilometers or 'q' for quit: ");
        inputString = scanner.nextLine();
        if (!inputString.equals("Q")) {
            distance = new BigDecimal(inputString);
        } else {
            System.out.println("\nBye!");
            return;
        }

        System.out.println("Value of weight = " + weight);
        System.out.println("Value of distance = " + distance);

        BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
        System.out.println("Price = " + price);
    }
}