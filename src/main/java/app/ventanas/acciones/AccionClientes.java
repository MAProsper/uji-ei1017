package app.ventanas.acciones;

import app.ventanas.interfaces.Accion;
import clientes.Cliente;
import clientes.ClienteEmpresa;
import clientes.ClientePaticular;
import helpers.clases.Direccion;
import helpers.interfaces.Factory;
import helpers.interfaces.FactoryClientes;
import tarifas.Tarifa;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;

public enum AccionClientes implements Accion, FactoryClientes {
    VER_CLIENTE("Ver cliente"),
    NUEVO_CLIENTE("Nuevo cliente", Cliente.class),
    NUEVO_PARTICULAR("Nuevo particular", ClientePaticular.class),
    NUEVO_EMPRESA("Nuevo empresa", ClienteEmpresa.class),
    VOLVER("Volver");

    private final String description;
    private final Class<? extends Cliente> clase;

    AccionClientes(final String description, final Class<? extends Cliente> clase) {
        this.description = stringNotEmpty("Descripcion", description);
        this.clase = clase;
    }

    AccionClientes(final String description) {
        this(description, null);
    }

    public String getDescription() {
        return description;
    }

    // Metodos para la fabrica de clientes
    @Override
    public Cliente getCliente(final String NIF, final String nombre, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa) {
        final Class<? extends Cliente> clase = referenceNotNull("Clase", this.clase);
        try {
            return clase.getConstructor(String.class, String.class, Direccion.class, String.class, LocalDateTime.class, Tarifa.class).newInstance(NIF, nombre, direccion, correo, fechaAlta, tarifa);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
            throw Factory.error(clase.getName());
        }
    }

    @Override
    public Cliente getCliente(final String NIF, final String nombre, final String apellidos, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa) {
        final Class<? extends Cliente> clase = referenceNotNull("Clase", this.clase);
        try {
            return clase.getConstructor(String.class, String.class, String.class, Direccion.class, String.class, LocalDateTime.class, Tarifa.class).newInstance(NIF, nombre, apellidos, direccion, correo, fechaAlta, tarifa);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
            throw Factory.error(clase.getName());
        }
    }
}