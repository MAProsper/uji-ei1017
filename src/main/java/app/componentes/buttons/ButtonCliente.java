package app.componentes.buttons;

import app.componentes.Button;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum ButtonCliente implements Button {
    VER_LLAMADAS("Ver llamadas"),
    VER_FACTURAS("Ver facturas"),
    ANYADIR_TARIFAS("Añadir tarifas"),
    BORRAR_CLIENTE("Borrar cliente"),
    VOLVER("Volver");

    private final String description;

    ButtonCliente(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}
