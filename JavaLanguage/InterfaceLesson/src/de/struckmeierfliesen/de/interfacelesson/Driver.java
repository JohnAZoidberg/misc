package de.struckmeierfliesen.de.interfacelesson;


import java.util.Random;

public class Driver {

    private Button[] buttons;

    public Driver() {
        Button button1 = new ChildButton();
        Button button2 = new Button();
        buttons = new Button[] {button1, button2};
    }

    public void drive() {
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            Button.threadSleep(500);
        }
    }

    public void setButtonOnPressedListener(int id, OnButtonPressedListener buttonPressedListener) {
        if (!(buttons[id] instanceof ChildButton))
          buttons[id].setOnButtonPressedListener(buttonPressedListener);
        else
          ((ChildButton) buttons[id]).setAnyOnButtonPressedListener(buttonPressedListener);
    }
}
