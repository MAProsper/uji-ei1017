package App.Ventanas;

import Helpers.Llamada;

import java.text.ParseException;
import java.util.Arrays;

import static Helpers.Fecha.parseDate;

public class VentanaLlamadaNueva extends Ventana {
    public VentanaLlamadaNueva() {
        super(
                "Llamadas",
                "Rellena los datos de la nueva llamada",
                false,
                Arrays.asList("Telefono", "Fecha (YYYY-MM-DD)", "Duracion (minutos)"),
                Arrays.asList("Crear", "Volver"));
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final String button) {
        Ventana ventana = null;

        switch (button) {
            case "Crear":
                Llamada llamada = crearLlamada();
                if (llamada == null) ventana = new VentanaError();
                else getGestor().addLlamada(llamada);
                break;
            case "Volver":
                break;
        }

        return ventana;
    }

    Llamada crearLlamada() {
        Llamada llamada = null;
        final String telefono = getTextbox("Telefono");
        final String fecha = getTextbox("Fecha (YYYY-MM-DD)");
        final String duracion = getTextbox("Duracion (minutos)");

        try {
            llamada = new Llamada(telefono, parseDate(fecha), Double.parseDouble(duracion));
        } catch (ParseException | IllegalArgumentException ignored) {
        }

        return llamada;
    }
}
