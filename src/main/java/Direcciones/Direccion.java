package Direcciones;

import static Helpers.HelperArgument.numberNotNegative;
import static Helpers.HelperArgument.stringNotEmpty;

public class Direccion {

    int codigo_postal;
    String provincia;
    String poblacion;

    public Direccion(int codigo_postal, String provincia, String poblacion) {
        this.codigo_postal = codigo_postal;
        this.provincia = stringNotEmpty("Provincia", provincia);
        this.poblacion = stringNotEmpty("Poblacion", poblacion);
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPoblacion() {
        return poblacion;
    }
}
