package app.ventanas.abstractas;

import app.ventanas.acciones.AccionRango;
import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;
import com.google.common.collect.Range;

import java.time.LocalDateTime;

import static helpers.estaticos.Arguments.referenceNotNull;

// Vista (abtracta para trabajar con rangos)
public abstract class VistaRango extends VistaPropia {
    private final Range<LocalDateTime> periodo;

    // Vista (define la vista contreta)
    public VistaRango(final Table[] table, final Range<LocalDateTime> perido) {
        super(
                "Listado en rango",
                "Reultados encontrados en el periodo",
                table, Textbox.empty(), AccionRango.values());

        this.periodo = referenceNotNull("Periodo", perido);
    }

    public final Range<LocalDateTime> getPeriodo() {
        return periodo;
    }
}
