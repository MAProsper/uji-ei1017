package app.ventanas.claeses;

import app.Parser;
import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Table;
import com.google.common.collect.Range;
import helpers.estaticos.Fecha;
import helpers.interfaces.Description;

import java.time.LocalDateTime;
import java.util.Optional;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VentanaRangoBuscar extends Ventana {
    protected final Tipo tipo;

    // Vista (define la vista contreta)
    public VentanaRangoBuscar(final Tipo tipo) {
        super(
                "Busqueda en rango",
                "Introduce un rango de fechas para buscar",
                Table.empty(), Textbox.values(), Button.values());
        this.tipo = referenceNotNull("Tipo", tipo);
    }

    public final Tipo getTipo() {
        return tipo;
    }

    public enum Tipo implements Description {
        CLIENTES("clientes"),
        FACTURAS("facturas"),
        LLAMADAS("llamadas");

        private final String description;

        Tipo(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        FECHA_INICIAL("Fecha inicial"),
        FECHA_FINAL("Fecha final");

        private final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Button implements app.ventanas.interfaces.Button {
        BUSCAR("Buscar"),
        VOLVER("Volver");

        private final String description;

        Button(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    protected Range<LocalDateTime> getPeriodo() {
        final String fechaInicio = getTextbox(Textbox.FECHA_INICIAL);
        final String fechaFinal = getTextbox(Textbox.FECHA_FINAL);
        return Fecha.getPeriodo(Parser.fecha(Textbox.FECHA_INICIAL.getDescription(), fechaInicio), Parser.fecha(Textbox.FECHA_INICIAL.getDescription(), fechaFinal));
    }

    // Controlador (define el controlador concreto)
    @Override
    public Optional<Gestionable> pressButton(final app.ventanas.interfaces.Button button) { // Gestiona la acción del usuario
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Gestionable ventana = null;

        switch ((Button) button) {
            case BUSCAR:
                // Vista.getPeriodo (2. solicita datos a la vista)
                ventana = VentanaError.attempt(this::getPeriodo, this::getVentana);
                break;
            case VOLVER:
                break;
        }

        return Optional.ofNullable(ventana);
    }

    protected Ventana getVentana(final Range<LocalDateTime> periodo) {
        referenceNotNull("Periodo", periodo);
        Ventana ventana;

        switch (tipo) {
            case CLIENTES:
                ventana = new VentanaClientesRango(periodo);
                break;
            case FACTURAS:
                ventana = new VentanaFacturasRango(periodo);
                break;
            case LLAMADAS:
                ventana = new VentanaLlamadasRango(periodo);
                break;
            default:
                throw new ValidationException("Button no clasificado");
        }

        return ventana;
    }

}
