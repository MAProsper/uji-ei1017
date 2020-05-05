package tarifas.generadores;

import helpers.clases.Llamada;
import helpers.generadores.GeneradorLlamada;
import tarifas.TarifaBase;

import java.util.Random;

public class GeneradorTarifaBase {
    protected final static Random genBase = new Random();
    protected final static GeneradorLlamada genLlamada = new GeneradorLlamada();

    public final double nextPrecio() {
        return genBase.nextDouble();
    }

    public final TarifaBase nextTarifa() {
        return new TarifaBase(nextPrecio());
    }

    public final Llamada nextLlamada() {
        return genLlamada.nextLlamada();
    }
}
