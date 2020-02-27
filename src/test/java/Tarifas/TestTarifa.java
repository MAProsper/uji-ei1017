package Tarifas;

import Helpers.Llamada;
import Tarifas.Generadores.GeneradorTarifa;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTarifa {
    static final GeneradorTarifa genTarifa = new GeneradorTarifa();

    @RepeatedTest(3)
    public void testTarifa() {
        final double precio = genTarifa.nextPrecio();
        System.out.println("Tarifa{" +
                "precio=" + precio +
                '}');
        final Tarifa tarifa = new Tarifa(precio);
        assertEquals(precio, tarifa.getPrecio());
    }

    @RepeatedTest(3)
    public void testTarifaGetImporte() {
        final Tarifa tarifa = genTarifa.nextTarifa();
        final Llamada llamada = genTarifa.nextLlamada();
        final double importe = llamada.getDuracion() * tarifa.getPrecio();
        System.out.println(tarifa + "\n" + llamada);
        assertEquals(importe, tarifa.getImporte(llamada));
    }
}
