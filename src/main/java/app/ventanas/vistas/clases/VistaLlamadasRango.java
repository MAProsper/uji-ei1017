package app.ventanas.vistas.clases;

import app.componentes.tables.TableLlamadas;
import app.helpers.estaticos.Formatter;
import app.ventanas.vistas.abstractas.VistaRango;
import com.google.common.collect.Range;
import helpers.clases.Llamada;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VistaLlamadasRango extends VistaRango {
    public VistaLlamadasRango(final Range<LocalDateTime> perido) {
        super(TableLlamadas.values(), perido);
    }

    @Override
    public void update() {
        final List<Llamada> llamadas = Fecha.filterRange(getModelo().getLlamadas(), getPeriodo());
        setTable(llamadas.stream().map(Formatter::format).collect(Collectors.toList()));
    }
}
