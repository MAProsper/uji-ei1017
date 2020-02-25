package Clientes;

import Direcciones.Direccion;
import Helpers.HelperGenerator;
import Tarifas.Tarifa;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClienteEmpresa extends TestCliente {
    @RepeatedTest(5)
    public void testClienteEmpresa() {
        final String nombre = generator.getNombre();
        final String NIF = generator.getNIF();
        final Direccion direccion = generator.getDireccion();
        final String correo = generator.getCorreo();
        final Date fechaAlta = generator.getFecha();
        final Tarifa tarifa = generator.getTarifa();
        System.out.println("ClienteEmpresa{" +
                "nombre='" + nombre + '\'' +
                ", NIF='" + NIF + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                '}');
        final ClienteEmpresa clienteEmpresa = new ClienteEmpresa(nombre, NIF, direccion, correo, fechaAlta, tarifa);
    }
}
