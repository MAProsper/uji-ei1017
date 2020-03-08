package app;

import helpers.Description;

public interface Button extends Description {
    static Button[] empty() {
        return new Button[]{};
    }
}
