package clientes;

import helpers.clases.Direccion;
import tarifas.Tarifa;

import java.time.LocalDate;

import static helpers.estaticos.Arguments.stringNotEmpty;

public class ClientePaticular extends Cliente {
    private static final long serialVersionUID = 9112248928365815263L;
    protected final String apellidos;

    public ClientePaticular(final String NIF, final String nombre, final String apellidos, final Direccion direccion, final String correo, final LocalDate fechaAlta, final Tarifa tarifa) {
        super(NIF, nombre, direccion, correo, fechaAlta, tarifa);
        this.apellidos = stringNotEmpty("Apellidos", apellidos);
    }

    final public String getApellidos() {
        return apellidos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "NIF='" + getNIF() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", apellidos='" + getNombre() + '\'' +
                ", direccion=" + getApellidos() +
                ", correo='" + getCorreo() + '\'' +
                ", fechaAlta=" + getFecha() +
                ", tarifa=" + getTarifa() +
                ", llamadas=" + getLlamadas() +
                ", facturas=" + getFacturas() +
                '}';
    }
}
