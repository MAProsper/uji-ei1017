package Clientes;

import Helpers.Direccion;
import Tarifas.Tarifa;

import java.util.Date;

public class ClienteEmpresa extends Cliente {
    public ClienteEmpresa(final String NIF, final String nombre, final Direccion direccion, final String correo, final Date fechaAlta, final Tarifa tarifa) {
        super(NIF, nombre, direccion, correo, fechaAlta, tarifa);
    }

    @Override
    public String toString() {
        return "ClienteEmpresa{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                ", llamadas=" + llamadas +
                ", facturas=" + facturas +
                '}';
    }
}
