package com.epam.brest2019.courses;


import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hi22");


        Main main = new Main ();
        System.out.println(main.getString());
        System.out.println(main.calc(12f, 13d));

        BigDecimal a = new BigDecimal("1.01");
        BigDecimal b = new BigDecimal("0.01");
        BigDecimal t = a.subtract(b);
        System.out.println("res: " + t);


    }

    public String getString(){
        return "TEST";

    }
    public  Double calc(Float f, Double d){
        return  f + d;
    }

} 

