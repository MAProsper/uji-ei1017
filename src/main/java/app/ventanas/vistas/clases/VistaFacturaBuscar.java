package app.ventanas.vistas.clases;

import app.componentes.Table;
import app.componentes.buttons.ButtonBuscar;
import app.componentes.textboxes.TextboxFacturaBuscar;
import app.helpers.estaticos.Parser;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorFacturaBuscar;
import app.ventanas.vistas.abstractas.VistaPropia;

public class VistaFacturaBuscar extends VistaPropia {
    public VistaFacturaBuscar() {
        super(
                "Busqueda",
                "Intoduzca el codigo de la factura",
                Table.empty(), TextboxFacturaBuscar.values(), ButtonBuscar.values());
    }

    @Override
    protected boolean validateControlador(final Controlador controlador) {
        return controlador instanceof ControladorFacturaBuscar;
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
