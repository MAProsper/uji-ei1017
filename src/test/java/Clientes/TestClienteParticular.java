package Clientes;

import java.util.Date;

import Clientes.Generadores.GeneradorClienteParticular;
import Direcciones.Direccion;
import Tarifas.Tarifa;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class TestClienteParticular extends TestCliente {
    static final GeneradorClienteParticular genClienteParticular = new GeneradorClienteParticular();

    @RepeatedTest(5)
    public void testClienteParticular() {
        final String nombre = genClienteParticular.nextNombre();
        final String apellidos = genClienteParticular.nextApellidos();
        final String NIF = genClienteParticular.nextNIF();
        final Direccion direccion = genClienteParticular.nextDireccion();
        final String correo = genClienteParticular.nextCorreo();
        final Date fechaAlta = genClienteParticular.nextFecha();
        final Tarifa tarifa = genClienteParticular.nextTarifa();
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
