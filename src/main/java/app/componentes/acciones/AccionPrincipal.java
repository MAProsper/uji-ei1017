package app.componentes.acciones;

import app.componentes.Accion;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum AccionPrincipal implements Accion {
    VER_CLIENTES("Ver clientes"),
    BUSCAR_CLIENTE_NIF("Buscar cliente (NIF)"),
    BUSCAR_CLIENTE_FACTURA("Buscar cliente (factura)"),
    BUSCAR_CLIENTE_RANGO("Buscar clientes (rango)"),
    BUSCAR_FACTURA_RANGO("Buscar facturas (rango)"),
    BUSCAR_LLAMADAS_RANGO("Buscar llamadas (rango)"),
    CARGAR("Cargar clientes"),
    GUARDAR("Guardar clientes"),
    CERRAR("Cerrar");

    private final String description;

    AccionPrincipal(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}