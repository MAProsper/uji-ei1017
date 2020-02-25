package Clientes;

import Direcciones.Direccion;
import Tarifas.Tarifa;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Date;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TestClienteParticular {

    public static Stream<Arguments> genericData() {
        return Stream.of(
                Arguments.of("Pablo", "Lopez Gallego", "46078060D", new Direccion(12590, "Castellon", "Almenara"), "Pablo@email.org", new Date() , new Tarifa(0.2))
        );
    }

    private static ClientePaticular cliente;
    private static int n = 0;

    @ParameterizedTest
    @MethodSource("genericData")
    public void TestClienteParticular(String nombre, String apellidos, String NIF, Direccion direccion, String correo, Date fecha, Tarifa tarifa){
        ClientePaticular cliente = new ClientePaticular(nombre, apellidos, NIF, direccion, correo, fecha, tarifa);

        System.out.println("\n---- Cliente numero " + n + " ----\n");

        System.out.println(cliente.nombre + " == " + nombre);
        assertEquals(cliente.nombre, nombre);
        System.out.println(cliente.apellidos + " == " + apellidos);
        assertEquals(cliente.apellidos, apellidos);
        System.out.println(cliente.NIF + " == " + NIF);
        assertEquals(cliente.NIF, NIF);
        System.out.println(cliente.direccion + " == " + direccion);
        assertEquals(cliente.direccion, direccion);
        System.out.println(cliente.correo + " == " + correo);
        assertEquals(cliente.correo, correo);
        System.out.println(cliente.fechaAlta + " == " + fecha);
        assertEquals(cliente.fechaAlta, fecha);
        System.out.println(cliente.tarifa.getTarifa() + " == " + tarifa.getTarifa());
        assertEquals(cliente.tarifa.getTarifa(), tarifa.getTarifa(), 0.1);
        n++;
    }

    @AfterAll
    public static void finish(){
        cliente = null;
    }


}
