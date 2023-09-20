package org.otus.javapro;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UnitTestRunner {
    private static final AnnotationDemoTest demoTest = new AnnotationDemoTest();

    private static final Logger log = LoggerFactory.getLogger(UnitTestRunner.class);

    public static void main(String[] args) {
       runAllTests(AnnotationDemoTest.class);
    }

    private static void runAllTests(Class<?> clazz) {

        try {
            for (Method method : clazz.getMethods()) {
                checkNumberOfAnnotations(method);
                if(method.getAnnotations().length == 0)
                    continue;
                Annotation testAnnotation = method.getAnnotations()[0];
                if(testAnnotation instanceof Test) {
                    runBeforeMethods(clazz.getMethods());
                    method.invoke(demoTest);
                    runAfterMethods(clazz.getMethods());
                }
            }
        } catch (Exception e) {
            log.error(e::getMessage);
            e.printStackTrace();
        }
    }

    private static void runBeforeMethods(Method[] methods) throws InvocationTargetException, IllegalAccessException {
        for (Method method: methods) {
            checkNumberOfAnnotations(method);
            if(method.getAnnotations().length == 0)
                continue;
            Annotation beforeAnnotation = method.getAnnotations()[0];
            if(beforeAnnotation instanceof BeforeEach)
                method.invoke(demoTest);
        }
    }

    private static void runAfterMethods(Method[] methods) throws InvocationTargetException, IllegalAccessException {
        for (Method method: methods) {
            checkNumberOfAnnotations(method);
            if(method.getAnnotations().length == 0)
                continue;
            Annotation beforeAnnotation = method.getAnnotations()[0];
            if(beforeAnnotation instanceof AfterEach)
                method.invoke(demoTest);
        }
    }

    private static void checkNumberOfAnnotations(Method method) {
        var length =  method.getAnnotations().length;
        if(length > 1)
            throw new RuntimeException("There are more then one annotation!");
    }


}