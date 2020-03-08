package tarifas;

import helpers.Llamada;
import org.junit.jupiter.api.RepeatedTest;
import tarifas.generadores.GeneradorTarifa;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTarifa {
    static final GeneradorTarifa generador = new GeneradorTarifa();

    @RepeatedTest(3)
    public void testTarifa() {
        final double precio = generador.nextPrecio();
        System.out.println("Tarifa{" +
                "precio=" + precio +
                '}');
        final Tarifa tarifa = new Tarifa(precio);
        assertEquals(precio, tarifa.getPrecio());
    }

    @RepeatedTest(3)
    public void testTarifaGetImporte() {
        final Tarifa tarifa = generador.nextTarifa();
        final Llamada llamada = generador.nextLlamada();
        final double importe = llamada.getDuracion() * tarifa.getPrecio();
        System.out.println(tarifa + "\n" + llamada);
        assertEquals(importe, tarifa.getImporte(llamada));
    }
}
