package clientes;

import helpers.clases.Direccion;
import tarifas.Tarifa;

import java.time.LocalDate;

import static helpers.estaticos.ValidatorArguments.stringNotEmpty;

public class ClientePaticular extends Cliente {
    private static final long serialVersionUID = 9112248928365815263L;
    final String apellidos;

    public ClientePaticular(final String NIF, final String nombre, final String apellidos, final Direccion direccion, final String correo, final LocalDate fechaAlta, final Tarifa tarifa) {
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
                ", llamadas=" + llamadas +
                ", facturas=" + facturas +
                '}';
    }
}
