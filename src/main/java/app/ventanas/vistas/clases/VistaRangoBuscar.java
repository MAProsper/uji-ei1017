package app.ventanas.vistas.clases;

import app.componentes.Table;
import app.componentes.buttons.ButtonBuscar;
import app.componentes.textboxes.TextboxRangoBuscar;
import app.helpers.estaticos.Parser;
import app.helpers.estaticos.TipoRangoBuscar;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorRangoBuscar;
import app.ventanas.vistas.abstractas.VistaPropia;
import com.google.common.collect.Range;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;

import static helpers.estaticos.Arguments.referenceNotNull;

public class VistaRangoBuscar extends VistaPropia {
    protected final TipoRangoBuscar tipo;

    public VistaRangoBuscar(final TipoRangoBuscar tipo) {
        super(
                "Busqueda en rango",
                "Introduce un rango de fechas para buscar",
                Table.empty(), TextboxRangoBuscar.values(), ButtonBuscar.values());
        this.tipo = referenceNotNull("Tipo", tipo);
    }

    @Override
    protected boolean validateControlador(final Controlador controlador) {
        return controlador instanceof ControladorRangoBuscar;
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorRangoBuscar();
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
