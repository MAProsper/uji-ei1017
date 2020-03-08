package app.ventanas;

import clientes.Cliente;
import clientes.ClienteEmpresa;
import helpers.Direccion;
import tarifas.Tarifa;

import java.text.ParseException;
import java.util.Arrays;

import static helpers.Fecha.parseDate;

public class VentanaClienteEmpresaNuevo extends Ventana {
    public VentanaClienteEmpresaNuevo() {
        super(
                "Cliente (empresa)",
                "Rellena los datos del nuevo cliente",
                false,
                Arrays.asList("NIF", "Nombre", "Codigo postal", "Provincia", "Poblacion", "Correo electronico", "Fecha de alta (YYYY-MM-DD)", "Tarifa base"),
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
        final String codigoPostal = getTextbox("Codigo postal");
        final String porvincia = getTextbox("Provincia");
        final String poblacion = getTextbox("Poblacion");
        final String correo = getTextbox("Correo electronico");
        final String fechaAlta = getTextbox("Fecha de alta (YYYY-MM-DD)");
        final String tarifaBase = getTextbox("Tarifa base");

        try {
            final Tarifa tarifa = new Tarifa(Double.parseDouble(tarifaBase));
            final Direccion direccion = new Direccion(Integer.parseInt(codigoPostal), porvincia, poblacion);
            cliente = new ClienteEmpresa(NIF, nombre, direccion, correo, parseDate(fechaAlta), tarifa);
        } catch (ParseException | IllegalArgumentException ignored) {
        }

        return cliente;
    }
}
