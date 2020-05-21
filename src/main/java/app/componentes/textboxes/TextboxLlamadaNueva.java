package app.componentes.textboxes;

import app.componentes.Textbox;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TextboxLlamadaNueva implements Textbox {
    TELEFONO("Telefono"),
    FECHA("Fecha"),
    DURACION("Duracion");
    private final String description;

    TextboxLlamadaNueva(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}
