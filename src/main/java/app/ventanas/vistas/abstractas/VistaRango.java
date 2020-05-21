package app.ventanas.vistas.abstractas;

import app.componentes.Table;
import com.google.common.collect.Range;

import java.time.LocalDateTime;

import static helpers.estaticos.Arguments.referenceNotNull;

public abstract class VistaRango extends VistaVolver {
    private final Range<LocalDateTime> periodo;

    public VistaRango(final Table[] table, final Range<LocalDateTime> perido) {
        super(
                "Listado en rango",
                "Reultados encontrados en el periodo");

        this.periodo = referenceNotNull("Periodo", perido);
    }

    public final Range<LocalDateTime> getPeriodo() {
        return periodo;
    }
}
