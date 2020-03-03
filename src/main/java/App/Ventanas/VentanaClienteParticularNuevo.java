package App.Ventanas;

import Clientes.Cliente;
import Clientes.ClientePaticular;
import Helpers.Direccion;
import Tarifas.Tarifa;

import java.text.ParseException;
import java.util.Arrays;

import static Helpers.Fecha.parseDate;

public class VentanaClienteParticularNuevo extends Ventana {
    public VentanaClienteParticularNuevo() {
        super(
                "Cliente (particular)",
                "Rellena los datos del nuevo cliente",
                false,
                Arrays.asList("NIF", "Nombre", "Apellidos", "Codigo postal", "Provincia", "Poblacion", "Correo electronico", "Fecha de alta (YYYY-MM-DD)", "Tarifa base"),
                Arrays.asList("Crear", "Volver"));
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final String button) {
        Ventana ventana = null;

        switch (button) {
            case "Crear":
                Cliente cliente = crearCliente();
                if (cliente == null) ventana = new VentanaError();
                else getGestor().addCliente(cliente);
                break;
            case "Volver":
                break;
        }

        return ventana;
    }

    Cliente crearCliente() {
        Cliente cliente = null;
        final String NIF = getTextbox("NIF");
        final String nombre = getTextbox("Nombre");
        final String apellidos = getTextbox("Apellidos");
        final String codigoPostal = getTextbox("Codigo postal");
        final String porvincia = getTextbox("Provincia");
        final String poblacion = getTextbox("Poblacion");
        final String correo = getTextbox("Correo electronico");
        final String fechaAlta = getTextbox("Fecha de alta (YYYY-MM-DD)");
        final String tarifaBase = getTextbox("Tarifa base");

        try {
            final Tarifa tarifa = new Tarifa(Double.parseDouble(tarifaBase));
            final Direccion direccion = new Direccion(Integer.parseInt(codigoPostal), porvincia, poblacion);
            cliente = new ClientePaticular(NIF, apellidos, nombre, direccion, correo, parseDate(fechaAlta), tarifa);
        } catch (ParseException | IllegalArgumentException ignored) {
        }

        return cliente;
    }
}