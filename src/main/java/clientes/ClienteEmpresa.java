package clientes;

import helpers.Direccion;
import tarifas.Tarifa;

import java.time.LocalDate;

public class ClienteEmpresa extends Cliente {
    public ClienteEmpresa(final String NIF, final String nombre, final Direccion direccion, final String correo, final LocalDate fechaAlta, final Tarifa tarifa) {
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
