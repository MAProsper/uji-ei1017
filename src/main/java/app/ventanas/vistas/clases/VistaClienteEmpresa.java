package app.ventanas.vistas.clases;

import app.helpers.estaticos.Formatter;
import clientes.ClienteEmpresa;
import helpers.clases.Direccion;

public class VistaClienteEmpresa extends VistaCliente {
    public VistaClienteEmpresa(final ClienteEmpresa cliente) {
        super(cliente);
    }

    @Override
    public void update() {
        final ClienteEmpresa cliente = (ClienteEmpresa) getCliente();
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
}
