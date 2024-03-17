package com.example.demo.util.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RefNoGenerator {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    // Generate a unique reference number
    public static String generateRefNo() {
        String dateTime = dateFormat.format(new Date());
        String randomNumber = generateRandomNumber();
        return dateTime + randomNumber;
    }

    // Generate a random 5-digit number
    private static String generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000) + 10000; // Generate a number between 10000 and 99999
        return String.valueOf(randomNumber);
    }
}
