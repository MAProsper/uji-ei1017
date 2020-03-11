package tarifas.generadores;

import helpers.clases.Llamada;
import helpers.generadores.GeneradorLlamada;
import tarifas.Tarifa;

import java.util.Random;

public class GeneradorTarifa {
    protected final static Random genBase = new Random();
    protected final static GeneradorLlamada genLlamada = new GeneradorLlamada();

    public final double nextPrecio() {
        return genBase.nextDouble();
    }

    public final Tarifa nextTarifa() {
        return new Tarifa(nextPrecio());
    }

    public final Llamada nextLlamada() {
        return genLlamada.nextLlamada();
    }
}
