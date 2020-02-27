package Facturas;

import Facturas.Generadores.GeneradorFactura;
import Tarifas.Tarifa;
import com.google.common.collect.Range;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class TestFactura {
    static final GeneradorFactura genFactura = new GeneradorFactura();

    @RepeatedTest(3)
    public void testFactura() {
        final int codigo = genFactura.nextCodigo();
        final Tarifa tarifa = genFactura.nextTarifa();
        final Date fechaEmision = genFactura.nextFecha();
        final Range<Date> periodo = genFactura.nextPeriodo();
        System.out.println("Factura{" +
                "codigo=" + codigo +
                ", tarifa=" + tarifa +
                ", fechaEmision=" + fechaEmision +
                ", periodo='" + periodo + '\'' +
                '}');
        final Factura factura = new Factura(codigo, tarifa, fechaEmision, periodo);
        assertEquals(codigo, factura.getCodigo());
        assertEquals(tarifa, factura.getTarifa());
        assertEquals(fechaEmision, factura.getFecha());
        assertEquals(periodo, factura.getPeriodo());
    }
}
