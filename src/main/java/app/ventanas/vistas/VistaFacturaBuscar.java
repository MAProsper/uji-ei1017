package app.ventanas.vistas;

import app.helpers.Parser;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.acciones.AccionFacturaBuscar;
import app.ventanas.controladores.ControladorFacturaBuscar;
import app.ventanas.interfaces.Table;
import app.ventanas.textboxes.TextboxFacturaBuscar;

import static helpers.estaticos.Arguments.validate;

public class VistaFacturaBuscar extends VistaPropia {
    public VistaFacturaBuscar() {
        super(
                "Busqueda",
                "Intoduzca el codigo de la factura",
                Table.empty(), TextboxFacturaBuscar.values(), AccionFacturaBuscar.values());
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
