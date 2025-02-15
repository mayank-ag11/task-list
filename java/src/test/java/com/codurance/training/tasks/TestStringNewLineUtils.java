package com.codurance.training.tasks;

public class TestStringNewLineUtils {
    public static String joinWithTrailingLineSeparator(String... lines) {
        return String.join(System.lineSeparator(), lines) + System.lineSeparator();
    }

    public static String joinWithoutTrailingLineSeparator(String... lines) {
        return String.join(System.lineSeparator(), lines);
    }
}
