package tarifas;

import helpers.clases.Llamada;
import org.junit.jupiter.api.RepeatedTest;
import tarifas.generadores.GeneradorTarifaBase;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTarifaBase {
    protected static final GeneradorTarifaBase generador = new GeneradorTarifaBase();

    @RepeatedTest(3)
    public void testTarifaBase() {
        final double precio = generador.nextPrecio();
        System.out.println("Tarifa{" +
                "precio=" + precio +
                '}');
        final Tarifa tarifa = new TarifaBase(precio);
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
