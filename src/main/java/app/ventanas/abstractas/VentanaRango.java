package app.ventanas.abstractas;

import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;
import com.google.common.collect.Range;

import java.time.LocalDateTime;
import java.util.Optional;

import static helpers.estaticos.Arguments.*;

// Vista (abtracta para trabajar con rangos)
public abstract class VentanaRango extends Ventana {
    private final Range<LocalDateTime> periodo;

    // Vista (define la vista contreta)
    public VentanaRango(final Table[] table, final Range<LocalDateTime> perido) {
        super(
                "Listado en rango",
                "Reultados encontrados en el periodo",
                table, Textbox.empty(), Button.values());

        this.periodo = referenceNotNull("Periodo", perido);
    }

    @Override
    abstract protected void update(); // Gestiona la notifiacion del modelo

    // Controlador (gestiona la acción del usuario)
    @Override
    public Optional<Gestionable> pressButton(final app.ventanas.interfaces.Button button) {
        validate("Button tiene que ser esta ventana", button instanceof Button);
        validate("Button no clasificado", button == Button.VOLVER);
        return Optional.empty();
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

    public Range<LocalDateTime> getPeriodo() {
        return periodo;
    }
}
