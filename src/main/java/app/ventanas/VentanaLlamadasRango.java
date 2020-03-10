package app.ventanas;

import app.Formato;
import app.Textbox;
import com.google.common.collect.Range;
import helpers.Fecha;
import helpers.Llamada;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaLlamadasRango extends Ventana {
    final Range<LocalDate> periodo;

    public VentanaLlamadasRango(final Range<LocalDate> perido) {
        super(
                "Llamadas (rango)",
                "Las llamadas en el periodo " + Formato.periodo(perido),
                true, Textbox.empty(), Button.values());
        this.periodo = perido;
    }

    @Override
    public void update() {
        final List<Llamada> llamadas = Fecha.filterRange(getGestor().getLlamadas(), periodo);
        setList(llamadas.stream().map(Formato::llamda).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(final app.Button button) {
        return null;
    }

    enum Button implements app.Button {

        VOLVER("volver");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

}
