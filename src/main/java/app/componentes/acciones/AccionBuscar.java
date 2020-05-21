package app.componentes.acciones;

import app.componentes.Accion;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum AccionBuscar implements Accion {
    BUSCAR("Buscar"),
    VOLVER("Volver");

    private final String description;

    AccionBuscar(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}