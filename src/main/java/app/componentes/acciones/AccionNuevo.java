package app.componentes.acciones;

import app.componentes.Accion;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum AccionNuevo implements Accion {
    CREAR("Crear"),
    VOLVER("Volver");

    private final String description;

    AccionNuevo(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}
