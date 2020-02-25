package Facturas;

import Helpers.HelperGenerator;
import Tarifas.Tarifa;
import com.google.common.collect.Range;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestFactura {
    static int codigos = 0;
    static final Random random = new Random();
    static final HelperGenerator generator = new HelperGenerator();

    @RepeatedTest(5)
    public void testFactura() {
        final int codigo = codigos++;
        final Tarifa tarifa = generator.getTarifa();
        final Date fechaEmision = generator.getFecha();
        final Range<Date> periodo = generator.getPeriodo();
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
