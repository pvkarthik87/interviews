package com.karcompany.pragmaticplay;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {

    // t1 (0), t2 (1), t3 (2)

    public static void f(int num) {
        aquireLock();
        if (count == num) {
            System.out.println(num + "Thread No:" + Thread.currentThread().getId());
            increment();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        releaseLock();
    }

    private static final Lock lock = new ReentrantLock();
    private static int count = 0;

    public static void print() {
        Thread t1 = new Thread(() -> {
            while (true) {
                f(0);
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                f(1);
            }
        });
        Thread t3 = new Thread(() -> {
            while (true) {
                f(2);
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public static void aquireLock() {
        lock.lock();
    }

    public static void increment() {
        count++;
        if (count > 2) {
            count = 0;
        }
    }

    public static void releaseLock() {
        lock.unlock();
    }

    public static void main(String[] args) {
        print();
    }
}
