package de.struckmeierfliesen.de.interfacelesson;

import java.util.Random;

public class Button {
    OnButtonPressedListener buttonPressedListener;

    public Button() {
        waitForButtonPress();
    }

    public void setOnButtonPressedListener(OnButtonPressedListener buttonPressedListener) {
        this.buttonPressedListener = buttonPressedListener;
    }

    public void waitForButtonPress() {
        new Thread() {
            public void run() {
                Random random = new Random();
                threadSleep(random.nextInt(5000) + 1000);
                // Button pressed
                if(buttonPressedListener != null) buttonPressedListener.onButtonPressed();
            }
        }.start();
    }

    public static void threadSleep(int ms) {
        Random random = new Random();
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Sleep error!");
        }
    }
}
