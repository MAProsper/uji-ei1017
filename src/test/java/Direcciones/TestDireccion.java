package Direcciones;

import Helpers.HelperGenerator;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class TestDireccion {
    static final HelperGenerator generator = new HelperGenerator();

    @RepeatedTest(5)
    public void testDirecion() {
        final String provincia = generator.getProvincia();
        final int codigoPostal = generator.getCodigoPostal(provincia);
        final String poblacion = generator.getPoblacion(provincia);
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
