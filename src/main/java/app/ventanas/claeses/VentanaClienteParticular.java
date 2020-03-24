package app.ventanas.claeses;

import app.Formatter;
import clientes.ClientePaticular;
import helpers.clases.Direccion;

import java.util.Arrays;

public class VentanaClienteParticular extends VentanaCliente {
    public VentanaClienteParticular(final ClientePaticular cliente) {
        super(cliente);
    }

    @Override
    protected void update() {
        final ClientePaticular cliente = (ClientePaticular) getCliente();
        final Direccion direccion = cliente.getDireccion();

        setList(Arrays.asList(
                "NIF: " + cliente.getNIF(),
                "Nombre: " + cliente.getNombre(),
                "Apellidos: " + cliente.getApellidos(),
                "Codigo postal: " + direccion.getCodigoPostal(),
                "Provincia: " + direccion.getProvincia(),
                "Poblacion: " + direccion.getPoblacion(),
                "Correo electronico : " + cliente.getCorreo(),
                "Fecha de alta: " + Formatter.format(cliente.getFecha()),
                "Tarifa base: " + cliente.getTarifa().getPrecio()
        ));
    }
}
