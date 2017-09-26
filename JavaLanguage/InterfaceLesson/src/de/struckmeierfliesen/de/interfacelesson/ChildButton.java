package de.struckmeierfliesen.de.interfacelesson;

public class ChildButton extends Button {

    public void setAnyOnButtonPressedListener(OnButtonPressedListener buttonPressedListener) {
        super.setOnButtonPressedListener(new MultiplePressedListener(buttonPressedListener, new OnButtonPressedListener() {
            @Override
            public void onButtonPressed() {
                System.out.println("Any Button pressed!");
            }
        }));
        super.setOnButtonPressedListener(new OnButtonPressedListener() {
            @Override
            public void onButtonPressed() {
                System.out.println("Any Button pressed!");
                buttonPressedListener.onButtonPressed();
            }
        });
    }
}