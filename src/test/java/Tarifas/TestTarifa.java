package Tarifas;

import Tarifas.Generadores.GeneradorTarifa;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class TestTarifa {
    static final GeneradorTarifa genTarifa = new GeneradorTarifa();

    @RepeatedTest(5)
    public void testTarifa() {
        final double precio = genTarifa.nextPrecio();
        System.out.println("Tarifa{" +
                "precio=" + precio +
                '}');
        final Tarifa tarifa = new Tarifa(precio);
        assertEquals(precio, tarifa.getPrecio());
    }
}
