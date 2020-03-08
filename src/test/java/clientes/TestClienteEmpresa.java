package clientes;

import clientes.generadores.GeneradorClienteEmpresa;
import helpers.Direccion;
import helpers.Factura;
import helpers.Llamada;
import org.junit.jupiter.api.RepeatedTest;
import tarifas.Tarifa;

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
        final Tarifa tarifa = generador.nextTarifa();
        final List<Llamada> llamadas = new LinkedList<>();
        final List<Factura> facturas = new LinkedList<>();
        System.out.println("ClienteEmpresa{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                ", llamadas=" + llamadas +
                ", facturas=" + facturas +
                '}');
        final ClienteEmpresa clienteEmpresa = new ClienteEmpresa(NIF, nombre, direccion, correo, fechaAlta, tarifa);
        assertEquals(NIF, clienteEmpresa.getNIF());
        assertEquals(nombre, clienteEmpresa.getNombre());
        assertEquals(direccion, clienteEmpresa.getDireccion());
        assertEquals(correo, clienteEmpresa.getCorreo());
        assertEquals(fechaAlta, clienteEmpresa.getFecha());
        assertEquals(tarifa, clienteEmpresa.getTarifa());
        assertEquals(facturas, clienteEmpresa.getFacturas());
        assertEquals(llamadas, clienteEmpresa.getLlamadas());
    }
}
