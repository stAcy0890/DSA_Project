package com.dsa.network;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TestJunitTest {

    TestJunit t;

    @BeforeEach
    void setUp() {
        t = new TestJunit(1);
    }

    @Test
    void getValue() {
        assertEquals(1, t.getValue());
    }

    @Test
    void setValue() {
        t.setValue(2);
        assertEquals(2, t.getValue());
    }

    @Test
    void testEquals() {
        TestJunit t_2 = new TestJunit(1);
        TestJunit t_3 = t;
        assertEquals(t_2, t);
    }

    @Test
    void testNotEquals() {
        TestJunit t_2 = new TestJunit(2);
        assertNotEquals(t_2, t);
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}