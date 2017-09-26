package de.struckmeierfliesen.de.interfacecourse;

import static de.struckmeierfliesen.de.interfacecourse.Button.*;

public class MultipleButtonPressedListener implements OnButtonPressedListener {
    OnButtonPressedListener[] listeners;

    public MultipleButtonPressedListener(OnButtonPressedListener... listeners) {
        this.listeners = listeners;
    }

    @Override
    public void onButtonPressed() {
        for (OnButtonPressedListener listener : listeners) {
            listener.onButtonPressed();
        }
    }
}
