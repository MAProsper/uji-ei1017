package clientes;

import helpers.clases.Direccion;
import tarifas.Tarifa;

import java.time.LocalDateTime;

public class ClienteEmpresa extends Cliente {
    private static final long serialVersionUID = -5942812838184931272L;

    public ClienteEmpresa(final String NIF, final String nombre, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa) {
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
                ", llamadas=" + llamadas +
                ", tarifa=" + tarifa +
                ", facturas=" + facturas +
                '}';
    }
}
