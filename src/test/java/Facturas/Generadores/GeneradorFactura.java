package Facturas.Generadores;

import Facturas.Factura;
import Helpers.HelperGenerator;
import Tarifas.Generadores.GeneradorTarifa;
import Tarifas.Tarifa;
import com.google.common.collect.Range;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GeneradorFactura {
    static int codigos = 0;
    final static HelperGenerator genHelper = new HelperGenerator();
    final static GeneradorTarifa genTarifa = new GeneradorTarifa();

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
        return Range.closed(Collections.min(fechas), Collections.max(fechas));
    }

    public Factura nextFactura() {
        return new Factura(nextCodigo(), nextTarifa(), nextFecha(), nextPeriodo());
    }
}
