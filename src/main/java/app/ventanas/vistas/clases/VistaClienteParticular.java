package app.ventanas.vistas.clases;

import app.helpers.estaticos.Formatter;
import clientes.ClientePaticular;
import helpers.clases.Direccion;

public class VistaClienteParticular extends VistaCliente {
    public VistaClienteParticular(final ClientePaticular cliente) {
        super(cliente);
    }

    @Override
    public void update() {
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
