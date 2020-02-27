package Helpers;

import Helpers.Generadores.GeneradorDireccion;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class TestDireccion {
    static final GeneradorDireccion genDireccion = new GeneradorDireccion();

    @RepeatedTest(3)
    public void testDirecion() {
        final String provincia = genDireccion.nextProvincia();
        final int codigoPostal = genDireccion.nextCodigoPostal(provincia);
        final String poblacion = genDireccion.nextPoblacion(provincia);
        System.out.println("Direccion{" +
                "codigoPostal=" + codigoPostal +
                ", provincia='" + provincia + '\'' +
                ", poblacion='" + poblacion + '\'' +
                '}');
        final Direccion direccion = new Direccion(codigoPostal, provincia, poblacion);
        assertEquals(codigoPostal, direccion.getCodigoPostal());
        assertEquals(provincia, direccion.getProvincia());
        assertEquals(poblacion, direccion.getPoblacion());
    }
}
