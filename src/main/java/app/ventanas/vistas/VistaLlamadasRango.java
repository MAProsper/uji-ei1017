package app.ventanas.vistas;

import app.helpers.Formatter;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaRango;
import app.ventanas.controladores.ControladorLlamadasRango;
import com.google.common.collect.Range;
import helpers.clases.Llamada;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.validate;

public class VistaLlamadasRango extends VistaRango {
    public VistaLlamadasRango(final Range<LocalDateTime> perido) {
        super(VistaLlamadas.Table.values(), perido);
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorLlamadasRango);
    }

    @Override
    public void update() {
        final List<Llamada> llamadas = Fecha.filterRange(getModelo().getLlamadas(), getPeriodo());
        setTable(llamadas.stream().map(Formatter::format).collect(Collectors.toList()));
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorLlamadasRango();
    }
}
