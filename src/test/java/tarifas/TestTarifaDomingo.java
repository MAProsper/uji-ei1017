package tarifas;

import helpers.clases.Llamada;
import org.junit.jupiter.api.RepeatedTest;
import tarifas.generadores.GeneradorTarifaDomingo;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTarifaDomingo extends TestTarifaBase {
    protected static final GeneradorTarifaDomingo generador = new GeneradorTarifaDomingo();

    @RepeatedTest(3)
    public void testTarifaGetImporte() {
        final Tarifa tarifa = generador.nextTarifa();
        final Llamada llamada = generador.nextLlamada();
        final double precio = generador.nextPrecio();
        final TarifaDomingo tarifaDomingo = new TarifaDomingo(tarifa, precio);
        final double importeBase = llamada.getDuracion() * tarifa.getPrecio();
        final double importeExtra = llamada.getDuracion() * tarifa.getPrecio();
        final double importe = Math.min(importeBase, importeExtra);
        final boolean aplicar = llamada.getFecha().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        System.out.println(tarifaDomingo + "\n" + llamada);
        assertEquals(aplicar ? importe : importeBase, tarifaDomingo.getImporte(llamada));
    }
}
