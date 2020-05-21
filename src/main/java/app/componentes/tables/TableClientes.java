package app.componentes.tables;

import app.componentes.Table;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TableClientes implements Table {
    NIF("NIF"),
    TIPO("Tipo"),
    NOMBRE("Nombre");

    private final String description;

    TableClientes(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}