package app.ventanas.interfaces;

import helpers.interfaces.Description;

public interface Table extends Description {
    static Table[] empty() {
        return new Table[]{};
    }
}
