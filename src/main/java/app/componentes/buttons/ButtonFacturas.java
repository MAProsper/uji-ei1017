package app.componentes.buttons;

import app.componentes.Button;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum ButtonFacturas implements Button {
    ANYADIR_FACTURA("Añadir factura"),
    VOLVER("Volver");

    private final String description;

    ButtonFacturas(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}
