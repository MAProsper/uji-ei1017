package Clientes;

import java.util.Date;

import Clientes.Generadores.GeneradorCliente;
import Direcciones.Direccion;
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
        System.out.println("Cliente{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                '}');
        final Cliente cliente = new Cliente(NIF, nombre, direccion, correo, fechaAlta, tarifa);
        assertEquals(NIF, cliente.getNIF());
        assertEquals(nombre, cliente.getNombre());
        assertEquals(direccion, cliente.getDireccion());
        assertEquals(correo, cliente.getCorreo());
        assertEquals(fechaAlta, cliente.getFecha());
        assertEquals(tarifa, cliente.getTarifa());
    }

    @RepeatedTest(3)
    public void testClienteSetTarifa() {
        Cliente cliente = genCliente.nextCliente();
        final Tarifa tarifa = genCliente.nextTarifa();
        System.out.println(cliente + "\n" + tarifa);
        cliente.setTarifa(tarifa);
        assertEquals(tarifa, cliente.getTarifa());
    }
}