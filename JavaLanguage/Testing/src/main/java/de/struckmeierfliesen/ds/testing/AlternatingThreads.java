package de.struckmeierfliesen.ds.testing;

import java.util.Random;

public class AlternatingThreads {

    public AlternatingThreads() {
        new ThreadOne().start();
    }

    public static class ThreadOne extends MyThread {
        public ThreadOne() {
            super("one");
        }

        @Override
        protected void startOtherThread() {
            new ThreadTwo().start();
        }
    }

    public static class ThreadTwo extends MyThread {
        public ThreadTwo() {
            super("two");
        }

        @Override
        protected void startOtherThread() {
            new ThreadOne().start();
        }
    }

    abstract static class MyThread extends Thread {
        private static Random random = new Random();

        public MyThread(String name) {
            setName(name);
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                System.out.println("Thread " + getName() + " is running!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (random.nextInt(3) == 0) {
                    interrupt();
                    System.out.println("Thread " + getName() + " was interrupted!");
                    startOtherThread();
                }
            }
        }

        abstract protected void startOtherThread();
    }
}