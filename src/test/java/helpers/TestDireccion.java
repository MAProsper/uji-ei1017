package helpers;

import helpers.clases.Direccion;
import helpers.generadores.GeneradorDireccion;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDireccion {
    protected static final GeneradorDireccion generador = new GeneradorDireccion();

    @RepeatedTest(3)
    public void testDirecion() {
        final String provincia = generador.nextProvincia();
        final int codigoPostal = generador.nextCodigoPostal(provincia);
        final String poblacion = generador.nextPoblacion(provincia);
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
