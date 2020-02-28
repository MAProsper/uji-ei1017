package Clientes.Generadores;

import Clientes.Cliente;
import Helpers.Direccion;
import Helpers.Factura;
import Helpers.Generadores.GeneradorDireccion;
import Helpers.Generadores.GeneradorFactura;
import Helpers.Generadores.GeneradorServicio;
import Helpers.Generadores.Generator;
import Helpers.Servicio;
import es.uji.www.GeneradorDatosINE;

import java.util.Date;

public class GeneradorCliente {
    final static Generator genHelper = new Generator();
    final static GeneradorDatosINE genINE = new GeneradorDatosINE();
    final static GeneradorDireccion genDireccion = new GeneradorDireccion();
    final static GeneradorServicio genServicio = new GeneradorServicio();
    final static GeneradorFactura genFactura = new GeneradorFactura();

    public String nextNombre() {
        return genINE.getNombre();
    }

    public String nextNIF() {
        return genINE.getNIF();
    }

    public Direccion nextDireccion() {
        return genDireccion.nextDireccion();
    }

    private static String stripNonWord(final String s) {
        return s.replaceAll("\\W", "_");
    }

    public String nextCorreo() {
        final String provincia = genINE.getProvincia();
        return stripNonWord(genINE.getNombre()) + "@" + stripNonWord(genINE.getPoblacion(provincia)) + "." + stripNonWord(provincia);
    }

    public Date nextFecha() {
        return genHelper.nextFecha();
    }

    public Servicio nextServicio() {
        return genServicio.nextServicio();
    }

    public Cliente nextCliente() {
        return new Cliente(nextNIF(), nextNombre(), nextDireccion(), nextCorreo(), nextFecha(), nextServicio());
    }

    public Factura nextFactura() {
        return genFactura.nextFactura();
    }
}
