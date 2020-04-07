package app.ventanas.interfaces;

import clientes.Cliente;
import helpers.clases.Direccion;
import tarifas.Tarifa;

import java.time.LocalDateTime;

public interface FactoryClientes extends Factory {
    Cliente getCliente(final String NIF, final String nombre, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa);

    Cliente getCliente(final String NIF, final String nombre, final String apellidos, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa);
}
