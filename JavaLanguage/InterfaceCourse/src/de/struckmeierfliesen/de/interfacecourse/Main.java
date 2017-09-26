package de.struckmeierfliesen.de.interfacecourse;

public class Main {

    public static void main(String... args) {
        //System.out.println("Program started!");
        //new Main();
        for (int i = 0; i <= 10000; i++) {
            final int j = i;
            new Thread() {
                @Override
                public void run() {
                    try {
                        //System.out.println("Thread " + j + " started working!");
                        sleep(500);
                        System.out.println("Thread " + j + " done working!");
                        return;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    public Main() {
        Driver driver = new Driver();
        driver.drive();
    }
}
