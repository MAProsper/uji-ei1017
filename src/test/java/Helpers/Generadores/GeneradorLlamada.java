package Helpers.Generadores;

import Helpers.Llamada;

import java.util.Date;
import java.util.Random;

public class GeneradorLlamada {
    final static Random genBase = new Random();
    final static Generator genHelper = new Generator();

    public String nextTelefono() {
        return Integer.toString(100000000 + genBase.nextInt(899999999));
    }

    public Date nextFecha() {
        return genHelper.nextFecha();
    }

    public double nextDuracion() {
        return genBase.nextDouble() * 99;
    }

    public Llamada nextLlamada() {
        return new Llamada(nextTelefono(), nextFecha(), nextDuracion());
    }
}