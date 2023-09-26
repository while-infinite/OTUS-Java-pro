package org.otus.javapro;


import org.otus.javapro.annotation.After;
import org.otus.javapro.annotation.Before;
import org.otus.javapro.annotation.Test;

public class AnnotationDemoTest {

    @Before
    public void beforeMethod() {
        System.out.println("invoke beforeMethod()");
    }

    @After
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
