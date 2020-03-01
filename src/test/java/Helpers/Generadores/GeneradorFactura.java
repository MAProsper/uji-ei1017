package Helpers.Generadores;

import Helpers.Factura;
import Tarifas.Generadores.GeneradorTarifa;
import Tarifas.Tarifa;
import com.google.common.collect.Range;

import java.util.*;

import static Helpers.Fecha.getPeriodo;

public class GeneradorFactura {
    final static Random genBase = new Random();
    final static Generator genHelper = new Generator();
    final static GeneradorTarifa genTarifa = new GeneradorTarifa();
    static int codigos = 0;

    public int nextCodigo() {
        return codigos++;
    }

    public Tarifa nextTarifa() {
        return genTarifa.nextTarifa();
    }

    public Date nextFecha() {
        return genHelper.nextFecha();
    }

    public Range<Date> nextPeriodo() {
        final List<Date> fechas = Arrays.asList(genHelper.nextFecha(), genHelper.nextFecha());
        return getPeriodo(Collections.min(fechas), Collections.max(fechas));
    }

    public double nextImporte() {
        return genBase.nextDouble() * 99;
    }

    public Factura nextFactura() {
        return new Factura(nextCodigo(), nextTarifa(), nextFecha(), nextPeriodo(), nextImporte());
    }
}
