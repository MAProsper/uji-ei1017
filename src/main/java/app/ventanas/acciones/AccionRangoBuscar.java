package app.ventanas.acciones;

import app.ventanas.interfaces.Accion;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum AccionRangoBuscar implements Accion {
    BUSCAR("Buscar"),
    VOLVER("Volver");

    private final String description;

    AccionRangoBuscar(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}