package Clientes;

import Helpers.Direccion;
import Helpers.Servicio;

import java.util.Date;

public class ClienteEmpresa extends Cliente {
    public ClienteEmpresa(final String NIF, final String nombre, final Direccion direccion, final String correo, final Date fechaAlta, final Servicio servicio) {
        super(NIF, nombre, direccion, correo, fechaAlta, servicio);
    }

    @Override
    public String toString() {
        return "ClienteEmpresa{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", servicio=" + servicio +
                ", facturas=" + facturas +
                '}';
    }
}
