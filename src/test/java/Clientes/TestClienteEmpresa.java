package Clientes;

import Clientes.Generadores.GeneradorClienteEmpresa;
import Helpers.Direccion;
import Helpers.Factura;
import Helpers.Servicio;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClienteEmpresa extends TestCliente {
    static final GeneradorClienteEmpresa generador = new GeneradorClienteEmpresa();

    @RepeatedTest(3)
    public void testClienteEmpresa() {
        final String NIF = generador.nextNIF();
        final String nombre = generador.nextNombre();
        final Direccion direccion = generador.nextDireccion();
        final String correo = generador.nextCorreo();
        final Date fechaAlta = generador.nextFecha();
        final Servicio servicio = generador.nextServicio();
        final List<Factura> facturas = new LinkedList<>();
        System.out.println("ClienteEmpresa{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", servicio=" + servicio +
                ", facturas=" + facturas +
                '}');
        final ClienteEmpresa clienteEmpresa = new ClienteEmpresa(NIF, nombre, direccion, correo, fechaAlta, servicio);
        assertEquals(NIF, clienteEmpresa.getNIF());
        assertEquals(nombre, clienteEmpresa.getNombre());
        assertEquals(direccion, clienteEmpresa.getDireccion());
        assertEquals(correo, clienteEmpresa.getCorreo());
        assertEquals(fechaAlta, clienteEmpresa.getFecha());
        assertEquals(servicio, clienteEmpresa.getServicio());
        assertEquals(facturas, clienteEmpresa.getFacturas());
    }
}
