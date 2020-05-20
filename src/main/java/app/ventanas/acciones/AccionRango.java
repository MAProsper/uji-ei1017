package app.ventanas.acciones;

import app.ventanas.interfaces.Accion;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum AccionRango implements Accion {
    VOLVER("Volver");

    private final String description;

    AccionRango(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}