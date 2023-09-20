package org.otus.javapro;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnnotationDemoTest {

    @BeforeEach
    public void beforeMethod() {
        System.out.println("invoke beforeMethod()");
    }

    @AfterEach
    public void afterMethod() {
        System.out.println("invoke afterMethod()");
    }

    @Test
    public void testOne() {
        System.out.println("invoke testOne()");
    }

    @Test
    public void testTwo() {
        System.out.println("invoke testTwo()");
    }
}
