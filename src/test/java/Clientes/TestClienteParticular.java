package Clientes;

import java.util.Date;

import Clientes.Generadores.GeneradorClienteParticular;
import Helpers.Direccion;
import Tarifas.Tarifa;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class TestClienteParticular extends TestCliente {
    static final GeneradorClienteParticular genClienteParticular = new GeneradorClienteParticular();

    @RepeatedTest(3)
    public void testClienteParticular() {
        final String NIF = genClienteParticular.nextNIF();
        final String nombre = genClienteParticular.nextNombre();
        final String apellidos = genClienteParticular.nextApellidos();
        final Direccion direccion = genClienteParticular.nextDireccion();
        final String correo = genClienteParticular.nextCorreo();
        final Date fechaAlta = genClienteParticular.nextFecha();
        final Tarifa tarifa = genClienteParticular.nextTarifa();
        System.out.println("ClientePaticular{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                '}');
        final ClientePaticular clientePaticular = new ClientePaticular(NIF, nombre, apellidos, direccion, correo, fechaAlta, tarifa);
        assertEquals(apellidos, clientePaticular.getApellidos());
    }
}
