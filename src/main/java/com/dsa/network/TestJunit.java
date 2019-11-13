package com.dsa.network;

import java.util.Objects;

public class TestJunit {

    private int value;

    public TestJunit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestJunit testJunit = (TestJunit) o;
        return value == testJunit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "TestJunit{" +
                "value=" + value +
                '}';
    }
}
