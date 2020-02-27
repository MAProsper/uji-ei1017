package Tarifas.Generadores;

import Helpers.Generadores.GeneradorLlamada;
import Helpers.Llamada;
import Tarifas.Tarifa;

import java.util.Random;

public class GeneradorTarifa {
    final static Random genBase = new Random();
    final static GeneradorLlamada genLlamada = new GeneradorLlamada();

    public double nextPrecio() {
        return genBase.nextDouble();
    }

    public Tarifa nextTarifa() {
        return new Tarifa(nextPrecio());
    }

    public Llamada nextLlamada() {
        return genLlamada.nextLlamada();
    }
}
