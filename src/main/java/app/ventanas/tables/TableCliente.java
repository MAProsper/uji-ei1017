package app.ventanas.tables;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TableCliente implements app.ventanas.interfaces.Table {
    ATRIBUTO("Atributo"),
    VALOR("Valor");

    private final String description;

    TableCliente(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}