package app.ventanas.claeses;

import app.ventanas.abstractas.VentanaNuevo;
import clientes.Cliente;
import helpers.clases.Llamada;
import helpers.estaticos.Parse;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;

public class VentanaLlamadaNueva extends VentanaNuevo {
    protected final Cliente cliente;

    public VentanaLlamadaNueva(final Cliente cliente) {
        super(Textbox.values());
        this.cliente = referenceNotNull("Cliente", cliente);
    }

    @Override
    protected void crear() {
        final String telefono = getTextbox(Textbox.TELEFONO);
        final String fecha = getTextbox(Textbox.FECHA);
        final String duracion = getTextbox(Textbox.DURACION);

        final Llamada llamada = new Llamada(telefono, Parse.fecha(Textbox.FECHA.getDescription(), fecha), Parse.real(Textbox.DURACION.getDescription(), duracion));

        cliente.addLlamada(llamada);
        getGestor().addLlamada(cliente, llamada);
    }

    public final Cliente getCliente() {
        return cliente;
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        TELEFONO("Telefono"),
        FECHA("Fecha (YYYY-MM-DD)"),
        DURACION("Duracion");
        private final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
