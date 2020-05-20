package app.ventanas.textboxes;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TextboxLlamadaNueva implements app.ventanas.interfaces.Textbox {
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
