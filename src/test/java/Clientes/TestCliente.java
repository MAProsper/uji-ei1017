package Clientes;

import Clientes.Generadores.GeneradorCliente;
import Helpers.Direccion;
import Helpers.Factura;
import Helpers.Llamada;
import Tarifas.Tarifa;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCliente {
    static final GeneradorCliente generador = new GeneradorCliente();

    @RepeatedTest(3)
    public void testCliente() {
        final String NIF = generador.nextNIF();
        final String nombre = generador.nextNombre();
        final Direccion direccion = generador.nextDireccion();
        final String correo = generador.nextCorreo();
        final Date fechaAlta = generador.nextFecha();
        final Tarifa tarifa = generador.nextTarifa();
        final List<Llamada> llamadas = new LinkedList<>();
        final List<Factura> facturas = new LinkedList<>();
        System.out.println("Cliente{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                ", llamadas=" + llamadas +
                ", facturas=" + facturas +
                '}');
        final Cliente cliente = new Cliente(NIF, nombre, direccion, correo, fechaAlta, tarifa);
        assertEquals(NIF, cliente.getNIF());
        assertEquals(nombre, cliente.getNombre());
        assertEquals(direccion, cliente.getDireccion());
        assertEquals(correo, cliente.getCorreo());
        assertEquals(fechaAlta, cliente.getFecha());
        assertEquals(tarifa, cliente.getTarifa());
        assertEquals(facturas, cliente.getFacturas());
        assertEquals(llamadas, cliente.getLlamadas());
    }

    @RepeatedTest(3)
    public void testClienteAddFactura() {
        Cliente cliente = generador.nextCliente();
        final Factura factura = generador.nextFactura();
        final List<Factura> facturas = new LinkedList<>();
        facturas.add(factura);
        System.out.println(cliente + "\n" + factura);
        cliente.addFactura(factura);
        assertEquals(facturas, cliente.getFacturas());
    }
}