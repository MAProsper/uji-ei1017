package Helpers.Generadores;

import Helpers.Direccion;
import es.uji.www.GeneradorDatosINE;

public class GeneradorDireccion {
    final static GeneradorDatosINE genINE = new GeneradorDatosINE();

    public int nextCodigoPostal(final String provincia) {
        return Math.abs(provincia.hashCode());
    }

    public String nextProvincia() {
        return genINE.getProvincia();
    }

    public String nextPoblacion(final String provincia) {
        return genINE.getPoblacion(provincia);
    }

    public Direccion nextDireccion() {
        final String provincia = nextProvincia();
        return new Direccion(nextCodigoPostal(provincia), provincia, nextPoblacion(provincia));
    }
}
