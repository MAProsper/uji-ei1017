package app.componentes.tables;

import app.componentes.Table;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TableLlamadas implements Table {
    FECHA("Fecha"),
    TELEFONO("Telefono"),
    DURACION("Duracion");

    private final String description;

    TableLlamadas(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}
