package app.componentes;

import helpers.interfaces.Description;

public interface Table extends Description {
    static Table[] empty() {
        return new Table[]{};
    }
}
