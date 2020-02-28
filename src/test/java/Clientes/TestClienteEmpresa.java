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
    static final GeneradorClienteEmpresa genClienteEmpresa = new GeneradorClienteEmpresa();

    @RepeatedTest(3)
    public void testClienteEmpresa() {
        final String NIF = genClienteEmpresa.nextNIF();
        final String nombre = genClienteEmpresa.nextNombre();
        final Direccion direccion = genClienteEmpresa.nextDireccion();
        final String correo = genClienteEmpresa.nextCorreo();
        final Date fechaAlta = genClienteEmpresa.nextFecha();
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
