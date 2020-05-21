package app.componentes;

import helpers.interfaces.Description;

public interface Textbox extends Description {
    static Textbox[] empty() {
        return new Textbox[]{};
    }
}
