package clientes;

import clientes.generadores.GeneradorClienteParticular;
import helpers.clases.Direccion;
import helpers.clases.Factura;
import helpers.clases.Llamada;
import org.junit.jupiter.api.RepeatedTest;
import tarifas.Tarifa;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClienteParticular extends TestCliente {
    protected static final GeneradorClienteParticular generador = new GeneradorClienteParticular();

    @RepeatedTest(3)
    public void testClienteParticular() {
        final String NIF = generador.nextNIF();
        final String nombre = generador.nextNombre();
        final String apellidos = generador.nextApellidos();
        final Direccion direccion = generador.nextDireccion();
        final String correo = generador.nextCorreo();
        final LocalDate fechaAlta = generador.nextFecha();
        final Tarifa tarifa = generador.nextTarifa();
        final List<Llamada> llamadas = new LinkedList<>();
        final List<Factura> facturas = new LinkedList<>();
        System.out.println("ClientePaticular{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                ", llamadas=" + llamadas +
                ", facturas=" + facturas +
                '}');
        final ClientePaticular clientePaticular = new ClientePaticular(NIF, nombre, apellidos, direccion, correo, fechaAlta, tarifa);
        assertEquals(NIF, clientePaticular.getNIF());
        assertEquals(nombre, clientePaticular.getNombre());
        assertEquals(apellidos, clientePaticular.getApellidos());
        assertEquals(direccion, clientePaticular.getDireccion());
        assertEquals(correo, clientePaticular.getCorreo());
        assertEquals(fechaAlta, clientePaticular.getFecha());
        assertEquals(tarifa, clientePaticular.getTarifa());
        assertEquals(facturas, clientePaticular.getFacturas());
        assertEquals(llamadas, clientePaticular.getLlamadas());
    }
}
