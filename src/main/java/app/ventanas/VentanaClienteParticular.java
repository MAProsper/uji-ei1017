package app.ventanas;

import clientes.ClientePaticular;
import helpers.Direccion;

import java.util.Arrays;
import java.util.Collections;

public class VentanaClienteParticular extends Ventana {
    public VentanaClienteParticular() {
        super(
                "Cliente (particular)",
                "Gestion del cliente",
                true,
                Collections.emptyList(),
                Arrays.asList("Ver llamadas", "Ver facturas", "Borrar cliente", "Volver"));
    }

    @Override
    public void update() {
        final ClientePaticular cliente = (ClientePaticular) getGestor().getClienteSelecionado();
        final Direccion direccion = cliente.getDireccion();

        setList(Arrays.asList(
                "NIF: " + cliente.getNIF(),
                "Nombre: " + cliente.getNombre(),
                "Apellidos: " + cliente.getApellidos(),
                "Codigo postal: " + direccion.getCodigoPostal(),
                "Provincia: " + direccion.getProvincia(),
                "Poblacion: " + direccion.getPoblacion(),
                "Correo electronico : " + cliente.getCorreo(),
                "Fecha de alta: " + cliente.getFecha(),
                "Tarifa base: " + cliente.getTarifa().getPrecio()
        ));
    }

    @Override
    public Ventana handle(final String button) {
        Ventana ventana = null;

        switch (button) {
            case "Ver llamadas":
                ventana = new VentanaLlamadas();
                break;
            case "Ver facturas":
                ventana = new VentanaFacturas();
                break;
            case "Borrar cliente":
                getGestor().removeCliente();
                break;
            case "Volver":
                break;
        }

        return ventana;
    }
}
