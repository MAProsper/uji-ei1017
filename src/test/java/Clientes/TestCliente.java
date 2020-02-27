package Clientes;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Clientes.Generadores.GeneradorCliente;
import Helpers.Direccion;
import Helpers.Factura;
import Helpers.Llamada;
import Tarifas.Tarifa;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class TestCliente {
    static final GeneradorCliente genCliente = new GeneradorCliente();

    @RepeatedTest(3)
    public void testCliente() {
        final String NIF = genCliente.nextNIF();
        final String nombre = genCliente.nextNombre();
        final Direccion direccion = genCliente.nextDireccion();
        final String correo = genCliente.nextCorreo();
        final Date fechaAlta = genCliente.nextFecha();
        final Tarifa tarifa = genCliente.nextTarifa();
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
        assertEquals(llamadas, cliente.getLlamadas());
        assertEquals(facturas, cliente.getFacturas());
    }

    @RepeatedTest(3)
    public void testClienteSetTarifa() {
        Cliente cliente = genCliente.nextCliente();
        final Tarifa tarifa = genCliente.nextTarifa();
        System.out.println(cliente + "\n" + tarifa);
        cliente.setTarifa(tarifa);
        assertEquals(tarifa, cliente.getTarifa());
    }

    @RepeatedTest(3)
    public void testClienteAddLlamada() {
        Cliente cliente = genCliente.nextCliente();
        final Llamada llamada = genCliente.nextLlamada();
        final List<Llamada> llamadas = new LinkedList<>();
        llamadas.add(llamada);
        System.out.println(cliente + "\n" + llamada);
        cliente.addLlamada(llamada);
        assertEquals(llamadas, cliente.getLlamadas());
    }

    @RepeatedTest(3)
    public void testClienteAddFactura() {
        Cliente cliente = genCliente.nextCliente();
        final Factura factura = genCliente.nextFactura();
        final List<Factura> facturas = new LinkedList<>();
        facturas.add(factura);
        System.out.println(cliente + "\n" + factura);
        cliente.addFactura(factura);
        assertEquals(facturas, cliente.getFacturas());
    }
}