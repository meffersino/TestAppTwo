package com.example.michael.testapptwo;

/**
 * A tester class for MainActivity.java
 * @see com.example.michael.testapptwo.MainActivity
 * @author Michael Jeffrey
 * @file MainActivityTester.java
 * @date 30/4/2017
 */

public class MainActivityTester {
    public static void main(String[] args) {


        String[] test1 = {"h", "e", "l", "l", "o", " ", "w", "o", "r", "l", "d"};
        String expected1 = "hello world";
        String result1 = stringArraytoString(test1);
        if(expected1.equals(result1)) {
            System.out.println("Test 1 successful");
        } else {
            System.out.println("Test 1 failed");
        }

    }

    /**
     * Converts a String array into a String
     * @param stringArray The String array to be converted
     * @return The converted String
     */
    public static String stringArraytoString(String[] stringArray) {
        String result ="";
        for(String x:stringArray) {
            result += x;
        }
        return result;
    }
}
