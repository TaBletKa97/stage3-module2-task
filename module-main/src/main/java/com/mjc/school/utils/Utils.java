package com.mjc.school.utils;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.mjc.school.utils.Constants.INCORRECT_VALUE;

public class Utils {

    public static long getLongFromScanner(Scanner sc, String message) {
        boolean isLong = false;
        long selectedVariant;
        System.out.println(message);
        do {
            try {
                selectedVariant = sc.nextLong();
                isLong = true;
            } catch (NoSuchElementException e) {
                System.err.println(INCORRECT_VALUE);
                selectedVariant = 0;
            } finally {
                sc.nextLine();
            }
        } while (!isLong);
        return selectedVariant;
    }
}
