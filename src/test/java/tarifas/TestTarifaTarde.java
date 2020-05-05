package tarifas;

import helpers.clases.Llamada;
import org.junit.jupiter.api.RepeatedTest;
import tarifas.generadores.GeneradorTarifaTarde;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTarifaTarde extends TestTarifaBase {
    protected static final GeneradorTarifaTarde generador = new GeneradorTarifaTarde();

    @RepeatedTest(3)
    public void testTarifaGetImporte() {
        final Tarifa tarifa = generador.nextTarifa();
        final Llamada llamada = generador.nextLlamada();
        final double precio = generador.nextPrecio();
        final TarifaTarde tarifaTarde = new TarifaTarde(tarifa, precio);
        final double importeBase = tarifa.getImporte(llamada);
        final double importeExtra = llamada.getDuracion() * tarifaTarde.getPrecio();
        final double importe = Math.min(importeBase, importeExtra);
        final boolean aplicar = tarifaTarde.getHorario().contains(llamada.getFecha().getHour());
        System.out.println(tarifaTarde + "\n" + llamada);
        assertEquals(aplicar ? importe : importeBase, tarifaTarde.getImporte(llamada));
    }
}
