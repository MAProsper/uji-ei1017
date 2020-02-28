package Helpers;

import Helpers.Generadores.GeneradorFactura;
import Tarifas.Tarifa;
import com.google.common.collect.Range;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFactura {
    static final GeneradorFactura generador = new GeneradorFactura();

    @RepeatedTest(3)
    public void testFactura() {
        final int codigo = generador.nextCodigo();
        final Tarifa tarifa = generador.nextTarifa();
        final Date fechaEmision = generador.nextFecha();
        final Range<Date> periodo = generador.nextPeriodo();
        final double importe = generador.nextImporte();
        System.out.println("Factura{" +
                "codigo=" + codigo +
                ", tarifa=" + tarifa +
                ", fechaEmision=" + fechaEmision +
                ", periodo=" + periodo +
                ", importe=" + importe +
                '}');
        final Factura factura = new Factura(codigo, tarifa, fechaEmision, periodo, importe);
        assertEquals(codigo, factura.getCodigo());
        assertEquals(tarifa, factura.getTarifa());
        assertEquals(fechaEmision, factura.getFecha());
        assertEquals(periodo, factura.getPeriodo());
        assertEquals(importe, factura.getImporte());
    }
}
