package app.componentes.buttons;

import app.componentes.Button;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum ButtonArchivo implements Button {
    PROCESAR("Procesar"),
    CANCELAR("Cancelar");

    private final String description;

    ButtonArchivo(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}