package app.componentes.buttons;

import app.componentes.Button;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum ButtonLlamadas implements Button {
    NUEVA_LLAMADA("Añadir llamada"),
    VOLVER("Volver");

    private final String description;

    ButtonLlamadas(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}
