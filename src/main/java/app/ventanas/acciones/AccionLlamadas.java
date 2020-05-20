package app.ventanas.acciones;

import app.ventanas.interfaces.Accion;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum AccionLlamadas implements Accion {
    NUEVA_LLAMADA("Añadir llamada"),
    VOLVER("Volver");

    private final String description;

    AccionLlamadas(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}
