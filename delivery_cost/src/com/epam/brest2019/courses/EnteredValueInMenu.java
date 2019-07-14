package com.epam.brest2019.courses;

import com.epam.brest2019.courses.menu.CorrectValue;
import com.epam.brest2019.courses.menu.EnteredValue;
import com.epam.brest2019.courses.menu.ExitValue;
import com.epam.brest2019.courses.menu.IncorrectValue;

import java.math.BigDecimal;
import java.util.Scanner;

public class EnteredValueInMenu {

    private static final String QUIT_SYMBOL = "q";

    public EnteredValue getReceiveValueFromConsole(String message, Scanner scanner) {
        EnteredValue enteredValue = new IncorrectValue();
        while (enteredValue.getType() == EnteredValue.Types.INCORRECT) {
            System.out.println(message);
            enteredValue = getParseInputValue(scanner.nextLine());
        }
        return enteredValue;
    }

    public EnteredValue getParseInputValue(String inputValue) {
        EnteredValue result = new ExitValue();
        if (!inputValue.trim().toLowerCase().equals(QUIT_SYMBOL)) {
            try {
                BigDecimal value = new BigDecimal(inputValue);
                if (value.compareTo(BigDecimal.ZERO) > 0) {
                    result = new CorrectValue(new BigDecimal(inputValue));
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.format("Incorrect value: %s%n", inputValue);
                result = new IncorrectValue();
            }
        }
        return result;
    }
}
