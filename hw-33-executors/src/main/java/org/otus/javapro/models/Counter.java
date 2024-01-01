package org.otus.javapro.models;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private boolean isReverseCount1;
    private boolean isReverseCount2;
    private int count1;
    private int count2;
    private final Lock lock = new ReentrantLock();
    private final Condition conditionRunFirst = lock.newCondition();
    private final Condition conditionRunSecond = lock.newCondition();

    public Counter() {
        this.count1 = 1;
        this.count2 = 1;
        this.isReverseCount1 = false;
        this.isReverseCount2 = false;
    }

    public void runCounter1() {
        while (true) {
            lock.lock();
            try {
                while (count1 != count2)
                    conditionRunFirst.await();

                if (!isReverseCount1) {
                    System.out.println(Thread.currentThread().getName() + ": " + ++count1);
                    if (count1 == 10)
                        isReverseCount1 = true;
                } else {
                    System.out.println(Thread.currentThread().getName() + ": " + --count1);
                    if (count1 == 1)
                        isReverseCount1 = false;
                }
                Thread.sleep(500);// just for convenient watching logs in console
                conditionRunSecond.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    public void runCounter2() {
        while (true) {
            lock.lock();
            try {
                while (count2 == count1)
                    conditionRunSecond.await();

                if (!isReverseCount2) {
                    System.out.println(Thread.currentThread().getName() + ": " + ++count2);
                    if (count2 == 10)
                        isReverseCount2 = true;
                } else {
                    System.out.println(Thread.currentThread().getName() + ": " + --count2);
                    if (count2 == 1)
                        isReverseCount2 = false;
                }
                conditionRunFirst.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
