package app.componentes.acciones;

import app.componentes.Accion;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum AccionFacturas implements Accion {
    ANYADIR_FACTURA("Añadir factura"),
    VOLVER("Volver");

    private final String description;

    AccionFacturas(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}
