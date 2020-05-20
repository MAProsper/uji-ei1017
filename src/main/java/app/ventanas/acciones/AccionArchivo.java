package app.ventanas.acciones;

import app.ventanas.interfaces.Accion;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum AccionArchivo implements Accion {
    PROCESAR("Procesar"),
    CANCELAR("Cancelar");

    private final String description;

    AccionArchivo(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}