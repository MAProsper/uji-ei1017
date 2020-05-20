package app.ventanas.vistas;

import app.helpers.Formatter;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaRango;
import app.ventanas.controladores.ControladorFacturasRango;
import com.google.common.collect.Range;
import helpers.clases.Factura;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.validate;

public class VistaFacturasRango extends VistaRango {
    public VistaFacturasRango(final Range<LocalDateTime> perido) {
        super(VistaFacturas.Table.values(), perido);
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorFacturasRango);
    }

    @Override
    public void update() {
        final List<Factura> facturas = Fecha.filterRange(getModelo().getFacturas(), getPeriodo());
        setTable(facturas.stream().map(Formatter::format).collect(Collectors.toList()));
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorFacturasRango();
    }
}
