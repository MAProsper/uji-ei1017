package Clientes;

import java.util.Date;

import Direcciones.Direccion;
import Helpers.HelperGenerator;
import Tarifas.Tarifa;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class TestCliente {
    static final HelperGenerator generator = new HelperGenerator();

    @RepeatedTest(5)
    public void testCliente() {
        final String nombre = generator.getNombre();
        final String NIF = generator.getNIF();
        final Direccion direccion = generator.getDireccion();
        final String correo = generator.getCorreo();
        final Date fechaAlta = generator.getFecha();
        final Tarifa tarifa = generator.getTarifa();
        System.out.println("Cliente{" +
                "nombre='" + nombre + '\'' +
                ", NIF='" + NIF + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                '}');
        final Cliente cliente = new Cliente(nombre, NIF, direccion, correo, fechaAlta, tarifa);
        assertEquals(nombre, cliente.getNombre());
        assertEquals(NIF, cliente.getNIF());
        assertEquals(direccion, cliente.getDireccion());
        assertEquals(correo, cliente.getCorreo());
        assertEquals(fechaAlta, cliente.getFecha());
        assertEquals(tarifa, cliente.getTarifa());
    }

    @RepeatedTest(5)
    public void testClienteSetTarifa() {
        Cliente cliente = generator.getCliente();
        final Tarifa tarifa = generator.getTarifa();
        System.out.println(cliente + "\n" + tarifa);
        cliente.setTarifa(tarifa);
        assertEquals(tarifa, cliente.getTarifa());
    }
}