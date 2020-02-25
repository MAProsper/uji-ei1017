package Direcciones;

import java.util.LinkedList;
import es.uji.www.GeneradorDatosINE;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestDireccion {
    public static Stream<Arguments> direcionData() {
        int cantidad = 5;
        GeneradorDatosINE generador = new GeneradorDatosINE();
        List<Arguments> datos = new LinkedList<>();

        for (int i = 0; i < cantidad; i++) {
            String provincia = generador.getProvincia();
            datos.add(Arguments.of(Math.abs(provincia.hashCode()) / 100000, provincia, generador.getPoblacion(provincia)));
        }

        return datos.stream();
    }

    @ParameterizedTest
    @MethodSource("direcionData")
    public void direcionTest(int codigo_postal, String provincia, String poblacion){
        Direccion dir = new Direccion(codigo_postal, provincia, poblacion);
        assertEquals(codigo_postal, dir.getCodigoPostal());
        assertEquals(provincia, dir.getProvincia());
        assertEquals(poblacion, dir.getPoblacion());
    }
}
