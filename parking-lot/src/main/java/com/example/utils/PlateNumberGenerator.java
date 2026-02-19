package com.example.utils;

import java.util.Random;

public class PlateNumberGenerator {
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    /**
     * Example format: ABC-1234 (3 letters + hyphen + 4 digits)
     */
    public static String generate() {
        StringBuilder sb = new StringBuilder(8);
        // Generate 3 random uppercase letters
        for (int i = 0; i < 3; i++) {
            sb.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        }
        sb.append('-');
        // Generate 4 random digits
        for (int i = 0; i < 4; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
}
