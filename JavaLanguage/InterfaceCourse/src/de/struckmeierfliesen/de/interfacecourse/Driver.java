package de.struckmeierfliesen.de.interfacecourse;

import java.util.Random;
import static de.struckmeierfliesen.de.interfacecourse.Button.*;

public class Driver {
    Button[] buttons;

    public Driver() {
        buttons = new Button[] { new ChildButton(), new Button() };

        OnButtonPressedListener listener0 = () -> System.out.println("Button 0 pressed!");
        OnButtonPressedListener listener1 = () -> System.out.println("Button 1 pressed!");

        for (Button button : buttons) {
            if (button instanceof ChildButton) {
                ((ChildButton) button).setOnAnyChildButtonPressedListener(() -> System.out.println("Normal Button pressed!"));
            } else {
                button.setOnButtonPressedListener(() -> System.out.println("Normal Button pressed!"));
            }
        }
    }

    public void drive() {
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            threadSleep(500);
        }
    }

    public static void threadSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Thread Error!");
        }
    }

}
