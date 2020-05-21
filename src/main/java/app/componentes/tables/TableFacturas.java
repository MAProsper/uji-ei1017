package app.componentes.tables;

import app.componentes.Table;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TableFacturas implements Table {
    CODIGO("Codigo"),
    FECHA("Fecha"),
    PERIODO("Periodo"),
    IMPORTE("Importe"),
    TARIFA("Tarifa (euro/min)");

    private final String description;

    TableFacturas(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}
