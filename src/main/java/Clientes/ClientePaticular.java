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

    public String getApellidos() {
        return apellidos;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final ClientePaticular that = (ClientePaticular) o;
        return Objects.equals(apellidos, that.apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), apellidos);
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
