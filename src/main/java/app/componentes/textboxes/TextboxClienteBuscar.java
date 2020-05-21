package app.componentes.textboxes;

import app.componentes.Textbox;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TextboxClienteBuscar implements Textbox {
    NIF("NIF");

    private final String description;

    TextboxClienteBuscar(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}