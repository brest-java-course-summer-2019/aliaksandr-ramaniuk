package com.epam.brest2019.courses.calculator;

import java.math.BigDecimal;

public class CalculateTotalPrice implements Calculator {

    @Override
    public BigDecimal totalPrice(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm) {
        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
    }
}
