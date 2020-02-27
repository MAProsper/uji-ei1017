package Clientes;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Clientes.Generadores.GeneradorClienteParticular;
import Helpers.Direccion;
import Helpers.Factura;
import Helpers.Llamada;
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
        assertEquals(llamadas, clientePaticular.getLlamadas());
        assertEquals(facturas, clientePaticular.getFacturas());
    }
}
