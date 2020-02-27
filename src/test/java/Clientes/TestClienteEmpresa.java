package Clientes;

import Clientes.Generadores.GeneradorClienteEmpresa;
import Helpers.Direccion;
import Helpers.Factura;
import Helpers.Llamada;
import Tarifas.Tarifa;
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
        final Tarifa tarifa = genClienteEmpresa.nextTarifa();
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
        assertEquals(llamadas, clienteEmpresa.getLlamadas());
        assertEquals(facturas, clienteEmpresa.getFacturas());
    }
}
