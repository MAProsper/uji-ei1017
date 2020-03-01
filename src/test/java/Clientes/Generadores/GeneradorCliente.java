package Clientes.Generadores;

import Clientes.Cliente;
import Helpers.Direccion;
import Helpers.Factura;
import Helpers.Generadores.GeneradorDireccion;
import Helpers.Generadores.GeneradorFactura;
import Helpers.Generadores.GeneradorLlamada;
import Helpers.Generadores.Generator;
import Helpers.Llamada;
import Tarifas.Generadores.GeneradorTarifa;
import Tarifas.Tarifa;
import es.uji.www.GeneradorDatosINE;

import java.util.Date;

public class GeneradorCliente {
    final static Generator genHelper = new Generator();
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

    public Date nextFecha() {
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
