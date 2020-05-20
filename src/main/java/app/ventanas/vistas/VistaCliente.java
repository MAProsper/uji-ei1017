package app.ventanas.vistas;

import app.helpers.Formatter;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.acciones.AccionCliente;
import app.ventanas.controladores.ControladorCliente;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;
import helpers.clases.Direccion;

import static helpers.estaticos.Arguments.*;

public class VistaCliente extends VistaPropia {
    protected final Cliente cliente;


    public VistaCliente(final Cliente cliente) {
        super(
                "Cliente",
                "Gestion del cliente",
                Table.values(), Textbox.empty(), AccionCliente.values());

        this.cliente = referenceNotNull("Cliente", cliente);
    }

    public enum Table implements app.ventanas.interfaces.Table {
        ATRIBUTO("Atributo"),
        VALOR("Valor");

        private final String description;

        Table(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    @Override
    protected Controlador validateControlador(Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorCliente);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorCliente();
    }

    @Override
    public void update() { // Gestiona la notificacion del modelo
        final Direccion direccion = cliente.getDireccion();

        setTable(new String[][]{
                {"NIF", cliente.getNIF()},
                {"Nombre", cliente.getNombre()},
                {"Codigo postal", Integer.toString(direccion.getCodigoPostal())},
                {"Provincia", direccion.getProvincia()},
                {"Poblacion", direccion.getPoblacion()},
                {"Correo electronico", cliente.getCorreo()},
                {"Fecha de alta", Formatter.format(cliente.getFecha())},
                {"Tarifa", cliente.getTarifa().toString()}
        });
    }

    public final Cliente getCliente() {
        return cliente;
    }

}
