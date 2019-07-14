package com.epam.brest2019.courses;


import com.epam.brest2019.courses.files.CSVFileReader;
import com.epam.brest2019.courses.files.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

class FileReaderValue {
    Map<Integer, BigDecimal> filePriceKm;
    Map<Integer, BigDecimal> filePriceKg;
    String FileNotFoundException = "File with prices per km not found";

    public Map<Integer, BigDecimal> getFilePriceKm() {
        return filePriceKm;
    }

    public Map<Integer, BigDecimal> getFilePriceKg() {
        return filePriceKg;
    }

    public void fileReaderValue() throws IOException {
        FileReader fileReader = new CSVFileReader();

        filePriceKm = fileReader.readData("price_per_km.csv");
        if (filePriceKm == null || filePriceKm.isEmpty()) {
            throw new FileNotFoundException(FileNotFoundException);
        }

        Map<Integer, BigDecimal> filePriceKg = fileReader.readData("price_per_kg.csv");
        if (filePriceKg == null || filePriceKg.isEmpty()) {
            throw new FileNotFoundException(FileNotFoundException);
        }

    }
}
