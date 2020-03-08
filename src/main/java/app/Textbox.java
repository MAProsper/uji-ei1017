package app;

import helpers.Description;

public interface Textbox extends Description {
    static Textbox[] empty() {
        return new Textbox[]{};
    }
}
