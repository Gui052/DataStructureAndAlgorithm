package interview.leetcode.solution;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程打印
 */
public class MultiThreadPrint {

    //两个线程交替打印1到100
    //使用lock版本
    private static final Lock lock = new ReentrantLock();
    private static final AtomicInteger sum = new AtomicInteger(0);
    private static final AtomicBoolean flag = new AtomicBoolean(true);

    public void printWithLock() throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            while (sum.get() < 100) {
                if (flag.get()) {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + ":" + sum.incrementAndGet());
                        flag.getAndSet(!flag.get());
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }, "threadOne-");
        Thread threadTwo = new Thread(() -> {
            while (sum.get() < 100) {
                if (!flag.get()) {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + ":" + sum.incrementAndGet());
                        flag.getAndSet(!flag.get());
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }, "threadTwo-");
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
    }

    //使用notify()和wait()实现
    public void printWithNotify() throws InterruptedException {
        //这里必须是一个print
        Print print = new Print();
        Thread one = new Thread(print, "ThreadOne-");
        Thread two = new Thread(print, "ThreadTwo-");
        one.start();
        two.start();
        one.join();
        two.join();
    }

    static class Print implements Runnable {
        private volatile int i = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    notify();
                    if (i < 100) {
                        i++;
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    } else {
                        break;
                    }
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
