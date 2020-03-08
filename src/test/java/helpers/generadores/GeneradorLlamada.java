package helpers.generadores;

import helpers.Llamada;

import java.time.LocalDate;
import java.util.Random;

public class GeneradorLlamada {
    final static Random genBase = new Random();
    final static GeneratorFecha genHelper = new GeneratorFecha();

    public String nextTelefono() {
        return Integer.toString(100000000 + genBase.nextInt(899999999));
    }

    public LocalDate nextFecha() {
        return genHelper.nextFecha();
    }

    public double nextDuracion() {
        return genBase.nextDouble() * 99;
    }

    public Llamada nextLlamada() {
        return new Llamada(nextTelefono(), nextFecha(), nextDuracion());
    }
}
