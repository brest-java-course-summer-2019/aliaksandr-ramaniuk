package com.epam.brest2019.courses;

import com.epam.brest2019.courses.calculator.Calculator;
import com.epam.brest2019.courses.calculator.CalculateTotalPrice;
import com.epam.brest2019.courses.menu.CorrectValue;
import com.epam.brest2019.courses.menu.EnteredValue;
import com.epam.brest2019.courses.selector.SelectorFromMap;
import com.epam.brest2019.courses.selector.ValueSelector;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {git

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        FileReaderValue fileReaderValue = new FileReaderValue();
        BigDecimal distance;
        BigDecimal weight;
        EnteredValue enteredValue;
        Calculator calculator = new CalculateTotalPrice();
        ValueSelector selector = new SelectorFromMap();
        EnteredValueInMenu enteredValueInMenu = new EnteredValueInMenu();

        do {
            System.out.println("==========================================================");
            enteredValue = enteredValueInMenu.getReceiveValueFromConsole("Enter weight in kg or 'q' for quit", scanner);
            if (enteredValue.getType() != EnteredValue.Types.EXIT) {
                weight = ((CorrectValue) enteredValue).getValue();
                enteredValue = enteredValueInMenu.getReceiveValueFromConsole("Enter distance in km or 'q' for quit", scanner);
                if (enteredValue.getType() != EnteredValue.Types.EXIT) {
                    distance = ((CorrectValue) enteredValue).getValue();

                    BigDecimal deliveryCost = calculator.totalPrice(weight, distance,
                            selector.selectValue(fileReaderValue.getFilePriceKg(), weight),
                            selector.selectValue(fileReaderValue.getFilePriceKm(), distance));

                    System.out.printf("Delivery cost: %.2f$\n",deliveryCost);

                }
            }
        } while (enteredValue.getType() != EnteredValue.Types.EXIT);
        System.out.println("Bye!");
    }

}