package com.quickcalc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CalcSession Integration Tests")
class CalcSessionIntegrationTest {

    private CalcSession session;

    @BeforeEach
    void setUp() {
        session = new CalcSession();
    }

    @Test
    @DisplayName("Integration: 5 + 3 = 8 full interaction flow")
    void testFullAdditionFlow() {
        session.enterNumber(5);
        session.pressOperator("+");
        session.enterNumber(3);
        session.pressEquals();
        assertEquals("8", session.getDisplay());
    }

    @Test
    @DisplayName("Integration: Clear after calculation resets display to '0'")
    void testClearResetsDisplay() {
        session.enterNumber(10);
        session.pressOperator("*");
        session.enterNumber(5);
        session.pressEquals();
        assertEquals("50", session.getDisplay());

        session.pressClear();
        assertEquals("0", session.getDisplay());
    }
}