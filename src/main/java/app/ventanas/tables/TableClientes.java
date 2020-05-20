package app.ventanas.tables;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TableClientes implements app.ventanas.interfaces.Table {
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