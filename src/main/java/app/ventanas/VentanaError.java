package app.ventanas;

import java.util.Collections;

public class VentanaError extends Ventana {
    public VentanaError() {
        super(
                "Error",
                "Los datos introducios son incorrectos",
                false,
                Collections.emptyList(),
                Collections.singletonList("Volver"));
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final String button) {
        return null;
    }
}
