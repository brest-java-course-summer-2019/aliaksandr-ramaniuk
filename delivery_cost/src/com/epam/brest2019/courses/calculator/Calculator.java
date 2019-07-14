package com.epam.brest2019.courses.calculator;

import java.math.BigDecimal;

public interface Calculator {
    BigDecimal totalPrice(BigDecimal pricePerKg, BigDecimal pricePerKm, BigDecimal weight, BigDecimal distance);
}
