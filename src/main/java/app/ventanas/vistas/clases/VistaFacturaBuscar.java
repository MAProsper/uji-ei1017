package app.ventanas.vistas.clases;

import app.componentes.Table;
import app.componentes.buttons.ButtonBuscar;
import app.componentes.textboxes.TextboxFacturaBuscar;
import app.helpers.estaticos.Parser;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorFacturaBuscar;
import app.ventanas.vistas.abstractas.VistaPropia;

import static helpers.estaticos.Arguments.validate;

public class VistaFacturaBuscar extends VistaPropia {
    public VistaFacturaBuscar() {
        super(
                "Busqueda",
                "Intoduzca el codigo de la factura",
                Table.empty(), TextboxFacturaBuscar.values(), ButtonBuscar.values());
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorFacturaBuscar);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorFacturaBuscar();
    }

    public final int getCodigo() {
        final String codigo = getTextbox(TextboxFacturaBuscar.CODIGO);
        return Parser.entreo(TextboxFacturaBuscar.CODIGO.getDescription(), codigo);
    }
}
