package Clientes;

import java.util.*;

import Tarifas.Tarifa;
import Direcciones.Direccion;

public class ClienteEmpresa extends Cliente {
    public ClienteEmpresa(final String nombre, final String NIF, final Direccion direccion, final String correo, final Date fechaAlta, final Tarifa tarifa) {
        super(nombre, NIF, direccion, correo, fechaAlta, tarifa);
    }

    @Override
    public String toString() {
        return "ClienteEmpresa{" +
                "nombre='" + nombre + '\'' +
                ", NIF='" + NIF + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                '}';
    }
}
