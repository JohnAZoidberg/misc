package de.struckmeierfliesen.de.interfacelesson;

public class Main {

    public static void main(String[] args) {
        System.out.println("Program started!");
        new Main();
    }

    public Main() {
        Driver driver = new Driver();
        driver.setButtonOnPressedListener(0, () -> System.out.println("Button 0 pressed!"));
        driver.setButtonOnPressedListener(1, () -> System.out.println("Button 1 pressed!"));
        driver.drive();
    }

    class Integer {
        int value;
        public Integer(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
