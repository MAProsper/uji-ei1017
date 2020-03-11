package app.ventanas.abstractas;

import app.ventanas.interfaces.Textbox;
import com.google.common.collect.Range;

import java.time.LocalDate;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;

public abstract class VentanaRango extends Ventana {
    private final Range<LocalDate> periodo;

    public VentanaRango(final Range<LocalDate> perido) {
        super(
                "Listado en rango",
                "Reultados encontrados en el periodo",
                true, Textbox.empty(), Button.values());

        this.periodo = referenceNotNull("periodo", perido);
    }

    @Override
    abstract protected void update();

    public Range<LocalDate> getPeriodo() {
        return periodo;
    }

    @Override
    public Ventana handle(final app.ventanas.interfaces.Button button) {
        return null;
    }

    private enum Button implements app.ventanas.interfaces.Button {
        VOLVER("Volver");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
