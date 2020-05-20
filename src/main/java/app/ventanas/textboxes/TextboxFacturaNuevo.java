package app.ventanas.textboxes;

import app.ventanas.interfaces.Textbox;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TextboxFacturaNuevo implements Textbox {
    CODIGO("Codigo"),
    FECHA_EMISION("Fecha de emision"),
    FECHA_INICIO("Fecha de inicio"),
    FECHA_FINAL("Fecha de final");
    private final String description;

    TextboxFacturaNuevo(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}
