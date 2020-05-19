package app.ventanas.claeses;

import app.Formatter;
import app.ventanas.abstractas.VentanaRango;
import com.google.common.collect.Range;
import helpers.clases.Llamada;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// Relacion Vista-Controlador
public class VentanaLlamadasRango extends VentanaRango {

    // Vista (define la vista contreta)
    public VentanaLlamadasRango(final Range<LocalDateTime> perido) {
        super(VentanaLlamadas.Table.values(), perido);
    }

    @Override
    protected void update() { // Gestiona la notificacion del modelo
        final List<Llamada> llamadas = Fecha.filterRange(getGestor().getLlamadas(), getPeriodo());
        setTable(llamadas.stream().map(Formatter::format).collect(Collectors.toList()));
    }
}
