package com.quickcalc;

/**
 * Represents one calculator session.
 * Holds current value, pending operator, and display state.
 */
public class CalcSession {

    private final Calculator calculator = new Calculator();
    private double currentValue = 0;
    private double storedValue  = 0;
    private String pendingOperator = null;
    private String display = "0";

    /** Enter a numeric value (replaces display). */
    public void enterNumber(double number) {
        currentValue = number;
        display = formatDisplay(number);
    }

    /** Set the pending operator (+, -, *, /). */
    public void pressOperator(String operator) {
        storedValue     = currentValue;
        pendingOperator = operator;
    }

    /** Execute the pending operation and update the display. */
    public void pressEquals() {
        if (pendingOperator == null) return;
        double result;
        switch (pendingOperator) {
            case "+" -> result = calculator.add(storedValue, currentValue);
            case "-" -> result = calculator.subtract(storedValue, currentValue);
            case "*" -> result = calculator.multiply(storedValue, currentValue);
            case "/" -> result = calculator.divide(storedValue, currentValue);
            default  -> throw new IllegalArgumentException("Unknown operator: " + pendingOperator);
        }
        currentValue    = result;
        display         = formatDisplay(result);
        pendingOperator = null;
    }

    /** Reset everything to the initial state. */
    public void pressClear() {
        currentValue    = 0;
        storedValue     = 0;
        pendingOperator = null;
        display         = "0";
    }

    public String getDisplay() { return display; }

    private String formatDisplay(double value) {
        // Show as integer when there is no fractional part
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}