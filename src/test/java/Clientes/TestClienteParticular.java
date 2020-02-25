package Clientes;

import java.util.Date;

import Direcciones.Direccion;
import Tarifas.Tarifa;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class TestClienteParticular extends TestCliente {
    @RepeatedTest(5)
    public void testClienteParticular() {
        final String nombre = generator.getNombre();
        final String apellidos = generator.getApellidos();
        final String NIF = generator.getNIF();
        final Direccion direccion = generator.getDireccion();
        final String correo = generator.getCorreo();
        final Date fechaAlta = generator.getFecha();
        final Tarifa tarifa = generator.getTarifa();
        System.out.println("ClientePaticular{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", NIF='" + NIF + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                '}');
        final ClientePaticular clientePaticular = new ClientePaticular(nombre, apellidos, NIF, direccion, correo, fechaAlta, tarifa);
        assertEquals(apellidos, clientePaticular.getApellidos());
    }
}
