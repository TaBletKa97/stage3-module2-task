package com.mjc.school.controller.utils;

import java.util.NoSuchElementException;
import java.util.Scanner;

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
                System.err.println(Constants.INCORRECT_VALUE);
                selectedVariant = 0;
            } finally {
                sc.nextLine();
            }
        } while (!isLong);
        return selectedVariant;
    }
}
