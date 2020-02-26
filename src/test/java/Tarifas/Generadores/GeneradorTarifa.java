package Tarifas.Generadores;

import Tarifas.Tarifa;

import java.util.Random;

public class GeneradorTarifa {
    final static Random genBase = new Random();

    public double nextPrecio() {
        return genBase.nextDouble();
    }

    public Tarifa nextTarifa() {
        return new Tarifa(nextPrecio());
    }
}
