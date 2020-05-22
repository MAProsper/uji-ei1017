package app.ventanas.controladores.clases;

import app.componentes.textboxes.TextboxLlamadaNueva;
import app.helpers.estaticos.Parser;
import app.ventanas.controladores.abstractas.ControladorNuevo;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaLlamadaNueva;
import clientes.Cliente;
import helpers.clases.Llamada;

public class ControladorLlamadaNueva extends ControladorNuevo {
    public ControladorLlamadaNueva() {
        super();
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaLlamadaNueva;
    }

    @Override
    protected void crear() {
        final VistaLlamadaNueva vistaActual = (VistaLlamadaNueva) getVista();
        final Cliente cliente = vistaActual.getCliente();

        final String telefono = vistaActual.getTextbox(TextboxLlamadaNueva.TELEFONO);
        final String fecha = vistaActual.getTextbox(TextboxLlamadaNueva.FECHA);
        final String duracion = vistaActual.getTextbox(TextboxLlamadaNueva.DURACION);

        final Llamada llamada = new Llamada(telefono, Parser.fecha(TextboxLlamadaNueva.FECHA.getDescription(), fecha), Parser.real(TextboxLlamadaNueva.DURACION.getDescription(), duracion));

        cliente.addLlamada(llamada);
        getModelo().addLlamada(cliente, llamada);
    }
}
