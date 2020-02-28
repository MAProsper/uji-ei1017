package Clientes;

import Helpers.Direccion;
import Helpers.Servicio;

import java.util.Date;

import static Helpers.ValidatorArguments.stringNotEmpty;

public class ClientePaticular extends Cliente {
    final String apellidos;

    public ClientePaticular(final String NIF, final String nombre, final String apellidos, final Direccion direccion, final String correo, final Date fechaAlta, final Servicio servicio) {
        super(NIF, nombre, direccion, correo, fechaAlta, servicio);
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
                ", servicio=" + servicio +
                ", facturas=" + facturas +
                '}';
    }
}
