package helpers.generadores;

import com.google.common.collect.Range;
import helpers.clases.Factura;
import tarifas.Tarifa;
import tarifas.generadores.GeneradorTarifaBase;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static helpers.estaticos.Fecha.getPeriodo;

public class GeneradorFactura {
    protected final static Random genBase = new Random();
    protected final static GeneratorFecha genHelper = new GeneratorFecha();
    protected final static GeneradorTarifaBase genTarifa = new GeneradorTarifaBase();
    private static int codigos = 0;

    public final int nextCodigo() {
        return codigos++;
    }

    public final Tarifa nextTarifa() {
        return genTarifa.nextTarifa();
    }

    public final LocalDateTime nextFecha() {
        return genHelper.nextFecha();
    }

    public final Range<LocalDateTime> nextPeriodo() {
        final List<LocalDateTime> fechas = Arrays.asList(genHelper.nextFecha(), genHelper.nextFecha());
        return getPeriodo(Collections.min(fechas), Collections.max(fechas));
    }

    public final double nextImporte() {
        return genBase.nextDouble() * 99;
    }

    public final Factura nextFactura() {
        return new Factura(nextCodigo(), nextTarifa(), nextFecha(), nextPeriodo(), nextImporte());
    }
}
