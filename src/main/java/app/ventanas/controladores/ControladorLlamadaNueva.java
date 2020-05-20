package app.ventanas.controladores;

import app.helpers.Parser;
import app.ventanas.abstractas.ControladorNuevo;
import app.ventanas.abstractas.Vista;
import app.ventanas.textboxes.TextboxLlamadaNueva;
import app.ventanas.vistas.VistaLlamadaNueva;
import clientes.Cliente;
import helpers.clases.Llamada;

import static helpers.estaticos.Arguments.validate;

public class ControladorLlamadaNueva extends ControladorNuevo {
    public ControladorLlamadaNueva() {
        super();
    }

    @Override
    protected Vista validateVista(Vista vista) {
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaLlamadaNueva);
    }

    @Override
    protected void crear() {
        final VistaLlamadaNueva vistaLlamadaNueva = (VistaLlamadaNueva) getVista();
        final Cliente cliente = vistaLlamadaNueva.getCliente();

        final String telefono = vistaLlamadaNueva.getTextbox(TextboxLlamadaNueva.TELEFONO);
        final String fecha = vistaLlamadaNueva.getTextbox(TextboxLlamadaNueva.FECHA);
        final String duracion = vistaLlamadaNueva.getTextbox(TextboxLlamadaNueva.DURACION);

        final Llamada llamada = new Llamada(telefono, Parser.fecha(TextboxLlamadaNueva.FECHA.getDescription(), fecha), Parser.real(TextboxLlamadaNueva.DURACION.getDescription(), duracion));

        cliente.addLlamada(llamada);
        getModelo().addLlamada(cliente, llamada);
    }
}
