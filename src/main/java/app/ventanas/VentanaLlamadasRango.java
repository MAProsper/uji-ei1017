package app.ventanas;

import app.Formato;
import com.google.common.collect.Range;
import helpers.Fecha;
import helpers.Llamada;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaLlamadasRango extends Ventana {
    final Range<Date> periodo;

    public VentanaLlamadasRango(final Range<Date> perido) {
        super(
                "Llamadas (rango)",
                "Las llamadas en el periodo " + Formato.periodo(perido),
                true,
                Collections.emptyList(),
                Collections.singletonList("Volver"));
        this.periodo = perido;
    }

    @Override
    public void update() {
        final List<Llamada> llamadas = Fecha.filterRange(getGestor().getLlamadas(), periodo);
        setList(llamadas.stream().map(Formato::llamda).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(final String button) {
        return null;
    }
}
