package app.ventanas.claeses;

import app.Formatter;
import clientes.ClienteEmpresa;
import helpers.clases.Direccion;

import java.util.Arrays;

public class VentanaClienteEmpresa extends VentanaCliente {
    public VentanaClienteEmpresa(final ClienteEmpresa cliente) {
        super(cliente);
    }

    @Override
    protected void update() {
        final ClienteEmpresa cliente = (ClienteEmpresa) getCliente();
        final Direccion direccion = cliente.getDireccion();

        setList(Arrays.asList(
                "NIF: " + cliente.getNIF(),
                "Nombre: " + cliente.getNombre(),
                "Codigo postal: " + direccion.getCodigoPostal(),
                "Provincia: " + direccion.getProvincia(),
                "Poblacion: " + direccion.getPoblacion(),
                "Correo electronico : " + cliente.getCorreo(),
                "Fecha de alta: " + Formatter.format(cliente.getFecha()),
                "Tarifa base: " + cliente.getTarifa()
        ));
    }
}
