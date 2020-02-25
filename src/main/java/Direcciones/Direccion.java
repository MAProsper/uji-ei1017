package Direcciones;

import java.util.HashMap;
import java.util.Map;

import static Helpers.HelperArgument.stringNotEmpty;

public class Direccion {

    int codigoPostal;
    String provincia;
    String poblacion;

    final static Map<String, Integer> provinciaCodigo = new HashMap<>();
    final static Map<Integer, String> codigoProvincia = new HashMap<>();

    public Direccion(int codigoPostal, String provincia, String poblacion) {
        if (provinciaCodigo.containsKey(provincia) && provinciaCodigo.get(provincia) != codigoPostal)
            throw new IllegalArgumentException(provincia + " tiene multiples codigos postales");

        if (codigoProvincia.containsKey(codigoPostal) && codigoProvincia.get(codigoPostal).equals(provincia))
            throw new IllegalArgumentException(codigoPostal + " tiene multiples provincia");

        this.codigoPostal = codigoPostal;
        this.provincia = stringNotEmpty("provincia", provincia);
        this.poblacion = stringNotEmpty("poblacion", poblacion);

        provinciaCodigo.put(provincia, codigoPostal);
        codigoProvincia.put(codigoPostal, poblacion);
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPoblacion() {
        return poblacion;
    }
}