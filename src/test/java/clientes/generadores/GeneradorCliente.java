package clientes.generadores;

import clientes.Cliente;
import es.uji.www.GeneradorDatosINE;
import helpers.clases.Direccion;
import helpers.clases.Factura;
import helpers.clases.Llamada;
import helpers.generadores.GeneradorDireccion;
import helpers.generadores.GeneradorFactura;
import helpers.generadores.GeneradorLlamada;
import helpers.generadores.GeneratorFecha;
import tarifas.Tarifa;
import tarifas.generadores.GeneradorTarifaBase;

import java.time.LocalDateTime;

public class GeneradorCliente {
    protected final static GeneratorFecha genHelper = new GeneratorFecha();
    protected final static GeneradorDatosINE genINE = new GeneradorDatosINE();
    protected final static GeneradorDireccion genDireccion = new GeneradorDireccion();
    protected final static GeneradorTarifaBase genTarifa = new GeneradorTarifaBase();
    protected final static GeneradorLlamada genLlamada = new GeneradorLlamada();
    protected final static GeneradorFactura genFactura = new GeneradorFactura();

    private static String stripNonWord(final String s) {
        return s.replaceAll("\\W", "_");
    }

    public final String nextNombre() {
        return genINE.getNombre();
    }

    public final String nextNIF() {
        // Puede dar problemas, numero limitado (podria repetir)
        return genINE.getNIF();
    }

    public final Direccion nextDireccion() {
        return genDireccion.nextDireccion();
    }

    public final String nextCorreo() {
        final String provincia = genINE.getProvincia();
        return String.format("%s@%s.%s",
                stripNonWord(nextNombre()),
                stripNonWord(genDireccion.nextPoblacion(provincia)),
                stripNonWord(provincia)
        ).toLowerCase();
    }

    public final LocalDateTime nextFecha() {
        return genHelper.nextFecha();
    }

    public final Tarifa nextTarifa() {
        return genTarifa.nextTarifa();
    }

    public final Cliente nextCliente() {
        return new Cliente(nextNIF(), nextNombre(), nextDireccion(), nextCorreo(), nextFecha(), nextTarifa());
    }

    public final Llamada nextLlamada() {
        return genLlamada.nextLlamada();
    }

    public final Factura nextFactura() {
        return genFactura.nextFactura();
    }
}
