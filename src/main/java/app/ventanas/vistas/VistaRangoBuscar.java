package app.ventanas.vistas;

import app.helpers.Parser;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.acciones.AccionRangoBuscar;
import app.ventanas.controladores.ControladorRangoBuscar;
import app.ventanas.helpers.TipoRangoBuscar;
import app.ventanas.interfaces.Table;
import app.ventanas.textboxes.TextboxRangoBuscar;
import com.google.common.collect.Range;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.validate;

public class VistaRangoBuscar extends VistaPropia {
    protected final TipoRangoBuscar tipo;

    public VistaRangoBuscar(final TipoRangoBuscar tipo) {
        super(
                "Busqueda en rango",
                "Introduce un rango de fechas para buscar",
                Table.empty(), TextboxRangoBuscar.values(), AccionRangoBuscar.values());
        this.tipo = referenceNotNull("Tipo", tipo);
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorRangoBuscar);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorRangoBuscar(getTipo());
    }

    public final TipoRangoBuscar getTipo() {
        return tipo;
    }

    public final Range<LocalDateTime> getPeriodo() {
        final String fechaInicio = getTextbox(TextboxRangoBuscar.FECHA_INICIAL);
        final String fechaFinal = getTextbox(TextboxRangoBuscar.FECHA_FINAL);
        return Fecha.getPeriodo(Parser.fecha(TextboxRangoBuscar.FECHA_INICIAL.getDescription(), fechaInicio), Parser.fecha(TextboxRangoBuscar.FECHA_INICIAL.getDescription(), fechaFinal));
    }
}
