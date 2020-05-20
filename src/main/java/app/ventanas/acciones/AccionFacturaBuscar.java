package app.ventanas.acciones;

import app.ventanas.interfaces.Accion;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum AccionFacturaBuscar implements Accion {
    BUSCAR("Buscar"),
    VOLVER("Volver");

    private final String description;

    AccionFacturaBuscar(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}