package app.ventanas.claeses;

import app.Formatter;
import app.ventanas.abstractas.VentanaRango;
import com.google.common.collect.Range;
import helpers.clases.Llamada;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaLlamadasRango extends VentanaRango {
    public VentanaLlamadasRango(final Range<LocalDateTime> perido) {
        super(perido);
    }

    @Override
    protected void update() {
        final List<Llamada> llamadas = Fecha.filterRange(getGestor().getLlamadas(), getPeriodo());
        setList(llamadas.stream().map(Formatter::format).collect(Collectors.toList()));
    }
}
