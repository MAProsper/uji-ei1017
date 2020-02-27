package Clientes;

import java.util.*;

import Tarifas.Tarifa;
import Direcciones.Direccion;

import static Helpers.HelperArgument.*;

public class ClientePaticular extends Cliente {
    final String apellidos;

    public ClientePaticular(final String NIF, final String nombre, final String apellidos, final Direccion direccion, final String correo, final Date fechaAlta, final Tarifa tarifa) {
        super(NIF, nombre, direccion, correo, fechaAlta, tarifa);
        this.apellidos = stringNotEmpty("apellidos", apellidos);
    }

    final public String getApellidos() {
        return apellidos;
    }

    @Override
    public String toString() {
        return "ClientePaticular{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                '}';
    }
}
