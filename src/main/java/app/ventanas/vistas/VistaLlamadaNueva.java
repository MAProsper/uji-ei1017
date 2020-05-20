package app.ventanas.vistas;

import app.helpers.Parser;
import app.ventanas.abstractas.VistaNuevo;
import clientes.Cliente;
import helpers.clases.Llamada;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;

// Relacion Vista-Controlador
public class VistaLlamadaNueva extends VistaNuevo {
    protected final Cliente cliente;

    // Vista (define la vista contreta)
    public VistaLlamadaNueva(final Cliente cliente) {
        super(Textbox.values());
        this.cliente = referenceNotNull("Cliente", cliente);
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        TELEFONO("Telefono"),
        FECHA("Fecha"),
        DURACION("Duracion");
        private final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public final Cliente getCliente() {
        return cliente;
    }

    // Controlador (define el controlador concreto)
    @Override
    protected void crear() {
        // Vista.getTextbox (2. solicita datos a la vista)
        final String telefono = getTextbox(Textbox.TELEFONO);
        final String fecha = getTextbox(Textbox.FECHA);
        final String duracion = getTextbox(Textbox.DURACION);

        final Llamada llamada = new Llamada(telefono, Parser.fecha(Textbox.FECHA.getDescription(), fecha), Parser.real(Textbox.DURACION.getDescription(), duracion));

        // Modelo.addLlamada (3. actualiza el modelo)
        cliente.addLlamada(llamada);
        getModelo().addLlamada(cliente, llamada);
    }
}
