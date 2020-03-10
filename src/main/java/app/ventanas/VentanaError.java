package app.ventanas;

import app.Textbox;

import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaError extends Ventana {
    public VentanaError() {
        super(
                "Error",
                "Los datos introducios son incorrectos",
                false, Textbox.empty(), Button.values());
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final app.Button button) {
        return null;
    }

    enum Button implements app.Button {
        ERROR("Error");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
