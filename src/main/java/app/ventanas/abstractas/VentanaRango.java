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

        this.periodo = referenceNotNull("Periodo", perido);
    }

    @Override
    abstract protected void update();

    public Range<LocalDate> getPeriodo() {
        return periodo;
    }

    public enum Button implements app.ventanas.interfaces.Button {
        VOLVER("Volver");

        private final String description;

        Button(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
