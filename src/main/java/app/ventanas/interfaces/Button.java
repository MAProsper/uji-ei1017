package app.ventanas.interfaces;

import helpers.interfaces.Description;

public interface Button extends Description {
    static Button[] empty() {
        return new Button[]{};
    }
}
