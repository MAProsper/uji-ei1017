package app.componentes.buttons;

import app.componentes.Button;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum ButtonBuscar implements Button {
    BUSCAR("Buscar"),
    VOLVER("Volver");

    private final String description;

    ButtonBuscar(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}