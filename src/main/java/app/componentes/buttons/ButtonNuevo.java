package app.componentes.buttons;

import app.componentes.Button;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum ButtonNuevo implements Button {
    CREAR("Crear"),
    VOLVER("Volver");

    private final String description;

    ButtonNuevo(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}
