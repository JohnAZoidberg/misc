package de.struckmeierfliesen.de.interfacecourse;


public class ChildButton extends Button {

    public void setOnAnyChildButtonPressedListener(OnButtonPressedListener listener) {
        OnButtonPressedListener genericListener = () -> System.out.println("Any Child Button pressed!");
        super.setOnButtonPressedListener(new MultipleButtonPressedListener(listener, genericListener));
    }

}
