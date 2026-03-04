package com.quickcalc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator Unit Tests")
class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    // ── Addition ─────────────────────────────────────────────
    @Test
    @DisplayName("Addition: 5 + 3 = 8")
    void testAddPositiveNumbers() {
        assertEquals(8.0, calc.add(5, 3));
    }

    // ── Subtraction ──────────────────────────────────────────
    @Test
    @DisplayName("Subtraction: 10 - 4 = 6")
    void testSubtractPositiveNumbers() {
        assertEquals(6.0, calc.subtract(10, 4));
    }

    @Test
    @DisplayName("Subtraction: result is negative")
    void testSubtractResultNegative() {
        assertEquals(-2.0, calc.subtract(3, 5));
    }

    // ── Multiplication ────────────────────────────────────────
    @Test
    @DisplayName("Multiplication: 6 × 7 = 42")
    void testMultiplyPositiveNumbers() {
        assertEquals(42.0, calc.multiply(6, 7));
    }

    @Test
    @DisplayName("Multiplication: multiply by zero = 0")
    void testMultiplyByZero() {
        assertEquals(0.0, calc.multiply(99, 0));
    }

    // ── Division ──────────────────────────────────────────────
    @Test
    @DisplayName("Division: 10 / 2 = 5")
    void testDividePositiveNumbers() {
        assertEquals(5.0, calc.divide(10, 2));
    }

    @Test
    @DisplayName("Division by zero throws ArithmeticException")
    void testDivideByZeroThrows() {
        assertThrows(ArithmeticException.class, () -> calc.divide(7, 0));
    }

    // ── Edge cases ────────────────────────────────────────────
    @Test
    @DisplayName("Edge case: decimal addition (0.1 + 0.2 ≈ 0.3)")
    void testAddDecimals() {
        assertEquals(0.3, calc.add(0.1, 0.2), 1e-9);
    }

    @Test
    @DisplayName("Edge case: large number multiplication")
    void testMultiplyLargeNumbers() {
        assertEquals(1_000_000.0, calc.multiply(1000, 1000));
    }

    @Test
    @DisplayName("Edge case: negative number subtraction")
    void testSubtractNegativeNumbers() {
        assertEquals(-1.0, calc.subtract(-3, -2));
    }
}