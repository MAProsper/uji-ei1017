package Clientes.Generadores;

import Clientes.Cliente;
import Direcciones.Direccion;
import Direcciones.Generadores.GeneradorDireccion;
import Helpers.HelperGenerator;
import Tarifas.Generadores.GeneradorTarifa;
import Tarifas.Tarifa;
import es.uji.www.GeneradorDatosINE;

import java.util.Date;

public class GeneradorCliente {
    final static HelperGenerator genHelper = new HelperGenerator();
    final static GeneradorDatosINE genINE = new GeneradorDatosINE();
    final static GeneradorDireccion genDireccion = new GeneradorDireccion();
    final static GeneradorTarifa genTarifa = new GeneradorTarifa();

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

    public Tarifa nextTarifa() {
        return genTarifa.nextTarifa();
    }

    public Cliente nextCliente() {
        return new Cliente(nextNombre(), nextNIF(), nextDireccion(), nextCorreo(), nextFecha(), nextTarifa());
    }
}
