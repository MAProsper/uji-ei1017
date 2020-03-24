package helpers.generadores;

import helpers.clases.Llamada;

import java.time.LocalDateTime;
import java.util.Random;

public class GeneradorLlamada {
    protected final static Random genBase = new Random();
    protected final static GeneratorFecha genHelper = new GeneratorFecha();

    public final String nextTelefono() {
        return Integer.toString(100000000 + genBase.nextInt(899999999));
    }

    public final LocalDateTime nextFecha() {
        return genHelper.nextFecha();
    }

    public final double nextDuracion() {
        return genBase.nextDouble() * 99;
    }

    public final Llamada nextLlamada() {
        return new Llamada(nextTelefono(), nextFecha(), nextDuracion());
    }
}
