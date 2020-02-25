package Tarifas;

import org.junit.jupiter.api.RepeatedTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestTarifa {
    static final Random random = new Random();

    @RepeatedTest(5)
    public void testTarifa() {
        final double tarifaValor = random.nextDouble() * 10;
        System.out.println("Tarifa{" +
                "tarifa=" + tarifaValor +
                '}');
        final Tarifa tarifa = new Tarifa(tarifaValor);
        assertEquals(tarifaValor, tarifa.getTarifa());
    }
}
