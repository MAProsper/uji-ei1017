package app.ventanas.acciones;

import app.ventanas.interfaces.Accion;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum AccionCliente implements Accion {
    VER_LLAMADAS("Ver llamadas"),
    VER_FACTURAS("Ver facturas"),
    ANYADIR_TARIFAS("Añadir tarifas"),
    BORRAR_CLIENTE("Borrar cliente"),
    VOLVER("Volver");

    private final String description;

    AccionCliente(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}
