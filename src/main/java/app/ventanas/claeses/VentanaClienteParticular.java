package app.ventanas.claeses;

import app.Formatter;
import clientes.ClientePaticular;
import helpers.clases.Direccion;

// Relacion Vista-Controlador
public class VentanaClienteParticular extends VentanaCliente {

    // Vista (define la vista contreta)
    public VentanaClienteParticular(final ClientePaticular cliente) {
        super(cliente);
    }

    @Override
    protected void update() { // Gestiona la notificacion del modelo
        final ClientePaticular cliente = (ClientePaticular) getCliente();
        final Direccion direccion = cliente.getDireccion();

        setTable(new String[][]{
                {"NIF", cliente.getNIF()},
                {"Nombre", cliente.getNombre()},
                {"Apellidos", cliente.getApellidos()},
                {"Codigo postal", Integer.toString(direccion.getCodigoPostal())},
                {"Provincia", direccion.getProvincia()},
                {"Poblacion", direccion.getPoblacion()},
                {"Correo electronico", cliente.getCorreo()},
                {"Fecha de alta", Formatter.format(cliente.getFecha())},
                {"Tarifa", cliente.getTarifa().toString()}
        });
    }
}
