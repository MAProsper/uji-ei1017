package clientes.generadores;

import clientes.Cliente;
import es.uji.www.GeneradorDatosINE;
import helpers.Direccion;
import helpers.Factura;
import helpers.Llamada;
import helpers.generadores.GeneradorDireccion;
import helpers.generadores.GeneradorFactura;
import helpers.generadores.GeneradorLlamada;
import helpers.generadores.GeneratorFecha;
import tarifas.Tarifa;
import tarifas.generadores.GeneradorTarifa;

import java.time.LocalDate;

public class GeneradorCliente {
    final static GeneratorFecha genHelper = new GeneratorFecha();
    final static GeneradorDatosINE genINE = new GeneradorDatosINE();
    final static GeneradorDireccion genDireccion = new GeneradorDireccion();
    final static GeneradorTarifa genTarifa = new GeneradorTarifa();
    final static GeneradorLlamada genLlamada = new GeneradorLlamada();
    final static GeneradorFactura genFactura = new GeneradorFactura();

    private static String stripNonWord(final String s) {
        return s.replaceAll("\\W", "_");
    }

    public String nextNombre() {
        return genINE.getNombre();
    }

    public String nextNIF() {
        return genINE.getNIF();
    }

    public Direccion nextDireccion() {
        return genDireccion.nextDireccion();
    }

    public String nextCorreo() {
        final String provincia = genINE.getProvincia();
        return stripNonWord(genINE.getNombre()) + "@" + stripNonWord(genINE.getPoblacion(provincia)) + "." + stripNonWord(provincia);
    }

    public LocalDate nextFecha() {
        return genHelper.nextFecha();
    }

    public Tarifa nextTarifa() {
        return genTarifa.nextTarifa();
    }

    public Cliente nextCliente() {
        return new Cliente(nextNIF(), nextNombre(), nextDireccion(), nextCorreo(), nextFecha(), nextTarifa());
    }

    public Llamada nextLlamada() {
        return genLlamada.nextLlamada();
    }

    public Factura nextFactura() {
        return genFactura.nextFactura();
    }
}
