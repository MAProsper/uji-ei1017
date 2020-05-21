package app.componentes.tables;

import app.componentes.Table;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TableAtributos implements Table {
    ATRIBUTO("Atributo"),
    VALOR("Valor");

    private final String description;

    TableAtributos(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}