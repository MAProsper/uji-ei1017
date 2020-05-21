package app.componentes.acciones;

import app.componentes.Accion;

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