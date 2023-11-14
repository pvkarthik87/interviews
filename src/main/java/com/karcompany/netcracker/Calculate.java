package com.karcompany.netcracker;

import java.util.Objects;

public class Calculate {

    public static void main(String[] args) {
        String[] exp = new String[] {
                "14",
                "+", "12",
                "-", "5",
                "*", "7",
                "/", "6",
        };
        System.out.println(evaluate(exp));
    }

    public static float evaluate(String[] exp) {
        String operation = null;
        float result = 0;
        for (String s : exp) {
            if (operation != null) {
                result = (performOperation(operation, result, Integer.parseInt(s)));
                operation = null;
            } else {
                if (s.equals("*") || s.equals("+") || s.equals("-") || s.equals("/")) {
                    operation = s;
                } else {
                    result += Integer.parseInt(s);
                }
            }
        }
        return result;
    }

    private static float performOperation(String operation, float a, int b) {
        if (Objects.equals(operation, "+")) {
            return a + b;
        }
        if (Objects.equals(operation, "-")) {
            return a - b;
        }
        if (Objects.equals(operation, "*")) {
            return a * b;
        }
        if (Objects.equals(operation, "/")) {
            return a / b;
        }
        throw new UnsupportedOperationException("not supported");
    }
}
