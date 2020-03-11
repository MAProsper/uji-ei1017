package helpers.generadores;

import es.uji.www.GeneradorDatosINE;
import helpers.clases.Direccion;

public class GeneradorDireccion {
    protected final static GeneradorDatosINE genINE = new GeneradorDatosINE();

    public final int nextCodigoPostal(final String provincia) {
        return Math.abs(provincia.hashCode());
    }

    public final String nextProvincia() {
        return genINE.getProvincia();
    }

    public final String nextPoblacion(final String provincia) {
        return genINE.getPoblacion(provincia);
    }

    public final Direccion nextDireccion() {
        final String provincia = nextProvincia();
        return new Direccion(nextCodigoPostal(provincia), provincia, nextPoblacion(provincia));
    }
}
