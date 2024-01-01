package org.otus.javapro;

import org.otus.javapro.models.Counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        Counter counter = new Counter();

        Runnable thread1 = (counter::runCounter1);
        Runnable thread2 = (counter::runCounter2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.shutdown();
    }
}