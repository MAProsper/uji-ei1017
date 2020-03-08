package app.ventanas;

import clientes.ClienteEmpresa;
import helpers.Direccion;

import java.util.Arrays;
import java.util.Collections;

public class VentanaClienteEmpresa extends Ventana {
    public VentanaClienteEmpresa() {
        super(
                "Cliente (empresa)",
                "Gestion del cliente",
                true,
                Collections.emptyList(),
                Arrays.asList("Ver llamadas", "Ver facturas", "Borrar cliente", "Volver"));
    }

    @Override
    public void update() {
        final ClienteEmpresa cliente = (ClienteEmpresa) getGestor().getClienteSelecionado();
        final Direccion direccion = cliente.getDireccion();

        setList(Arrays.asList(
                "NIF: " + cliente.getNIF(),
                "Nombre: " + cliente.getNombre(),
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
