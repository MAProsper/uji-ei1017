package app.componentes.textboxes;

import app.componentes.Textbox;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TextboxFacturaBuscar implements Textbox {
    CODIGO("Codigo");

    private final String description;

    TextboxFacturaBuscar(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}