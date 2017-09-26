package de.struckmeierfliesen.de.interfacelesson;

public class MultiplePressedListener implements OnButtonPressedListener {
    OnButtonPressedListener[] listeners;

    public MultiplePressedListener(OnButtonPressedListener... listeners) {
        setOnPressedListeners(listeners);
    }

    @Override
    public void onButtonPressed() {
        for (OnButtonPressedListener listener : listeners) listener.onButtonPressed();
    }

    public void setOnPressedListeners(OnButtonPressedListener... listeners) {
        this.listeners = listeners;
    }
}
