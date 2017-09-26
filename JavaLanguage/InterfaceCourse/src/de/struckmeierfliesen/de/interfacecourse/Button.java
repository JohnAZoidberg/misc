package de.struckmeierfliesen.de.interfacecourse;

import java.util.Random;

public class Button {

    OnButtonPressedListener listener;

    public Button() {
        waitForButtonPress();
    }

    private void pressButton() {
        Random random = new Random();
        Driver.threadSleep(random.nextInt(4000) + 1000);
    }

    public void setOnButtonPressedListener(OnButtonPressedListener listener) {
        this.listener = listener;
    }

    public void waitForButtonPress() {
        new Thread() {
            @Override
            public void run() {
                pressButton();
                if(listener != null) listener.onButtonPressed();
            }
        }.start();
    }

    interface OnButtonPressedListener {
        void onButtonPressed();
    }
}
