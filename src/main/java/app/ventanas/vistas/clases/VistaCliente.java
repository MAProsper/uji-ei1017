package app.ventanas.vistas.clases;

import app.componentes.Textbox;
import app.componentes.acciones.AccionCliente;
import app.componentes.tables.TableAtributos;
import app.helpers.estaticos.Formatter;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorCliente;
import app.ventanas.vistas.abstractas.VistaPropia;
import clientes.Cliente;
import helpers.clases.Direccion;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.validate;

public class VistaCliente extends VistaPropia {
    protected final Cliente cliente;

    public VistaCliente(final Cliente cliente) {
        super(
                "Cliente",
                "Gestion del cliente",
                TableAtributos.values(), Textbox.empty(), AccionCliente.values());

        this.cliente = referenceNotNull("Cliente", cliente);
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorCliente);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorCliente();
    }

    @Override
    public void update() {
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
