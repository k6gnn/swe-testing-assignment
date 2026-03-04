package com.quickcalc;

import java.util.Scanner;

/**
 * Command-line interface for Quick-Calc.
 * Usage: enter two numbers and an operator when prompted.
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();

        System.out.println("=== Quick-Calc ===");
        System.out.println("Operators: + - * /   |  Type 'exit' to quit\n");

        while (true) {
            System.out.print("Enter first number (or 'exit'): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;

            double a;
            try { a = Double.parseDouble(input); }
            catch (NumberFormatException e) { System.out.println("Invalid number.\n"); continue; }

            System.out.print("Enter operator (+, -, *, /): ");
            String op = scanner.nextLine().trim();

            System.out.print("Enter second number: ");
            double b;
            try { b = Double.parseDouble(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Invalid number.\n"); continue; }

            try {
                double result = switch (op) {
                    case "+" -> calc.add(a, b);
                    case "-" -> calc.subtract(a, b);
                    case "*" -> calc.multiply(a, b);
                    case "/" -> calc.divide(a, b);
                    default  -> throw new IllegalArgumentException("Unknown operator.");
                };
                System.out.printf("Result: %s %s %s = %s%n%n",
                    formatNum(a), op, formatNum(b), formatNum(result));
            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private static String formatNum(double v) {
        return (v == Math.floor(v) && !Double.isInfinite(v))
            ? String.valueOf((long) v) : String.valueOf(v);
    }
}