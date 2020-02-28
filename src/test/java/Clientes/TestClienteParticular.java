package Clientes;

import Clientes.Generadores.GeneradorClienteParticular;
import Helpers.Direccion;
import Helpers.Factura;
import Helpers.Servicio;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        final Servicio servicio = generador.nextServicio();
        final List<Factura> facturas = new LinkedList<>();
        System.out.println("ClientePaticular{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", servicio=" + servicio +
                ", facturas=" + facturas +
                '}');
        final ClientePaticular clientePaticular = new ClientePaticular(NIF, nombre, apellidos, direccion, correo, fechaAlta, servicio);
        assertEquals(NIF, clientePaticular.getNIF());
        assertEquals(nombre, clientePaticular.getNombre());
        assertEquals(apellidos, clientePaticular.getApellidos());
        assertEquals(direccion, clientePaticular.getDireccion());
        assertEquals(correo, clientePaticular.getCorreo());
        assertEquals(fechaAlta, clientePaticular.getFecha());
        assertEquals(servicio, clientePaticular.getServicio());
        assertEquals(facturas, clientePaticular.getFacturas());
    }
}
