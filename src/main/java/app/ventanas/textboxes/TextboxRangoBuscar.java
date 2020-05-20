package app.ventanas.textboxes;

import app.ventanas.interfaces.Textbox;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TextboxRangoBuscar implements Textbox {
    FECHA_INICIAL("Fecha inicial"),
    FECHA_FINAL("Fecha final");

    private final String description;

    TextboxRangoBuscar(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}