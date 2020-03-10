package app.ventanas;

import helpers.Llamada;

import java.text.ParseException;

import static helpers.Fecha.parse;
import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaLlamadaNueva extends Ventana {
    public VentanaLlamadaNueva() {
        super(
                "Llamadas",
                "Rellena los datos de la nueva llamada",
                false, Textbox.values(), Button.values());
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final app.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case CREAR:
                Llamada llamada = crearLlamada();
                if (llamada == null) ventana = new VentanaError();
                else getGestor().gestionarLlamada(llamada);
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    Llamada crearLlamada() {
        Llamada llamada = null;
        final String telefono = getTextbox(Textbox.TELEFONO);
        final String fecha = getTextbox(Textbox.FECHA);
        final String duracion = getTextbox(Textbox.DURACION);

        try {
            llamada = new Llamada(telefono, parse(fecha), Double.parseDouble(duracion));
        } catch (ParseException | IllegalArgumentException ignored) {
        }

        return llamada;
    }

    enum Button implements app.Button {
        CREAR("crear"),
        VOLVER("volver");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    enum Textbox implements app.Textbox {
        TELEFONO("Telefono"),
        FECHA("Fecha (YYYY-MM-DD)"),
        DURACION("Duracion");
        final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
