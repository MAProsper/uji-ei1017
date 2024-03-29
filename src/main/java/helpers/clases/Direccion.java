package helpers.clases;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.Serializable;
import java.util.Objects;

import static helpers.estaticos.Arguments.*;

public class Direccion implements Serializable {
    private final static BiMap<String, Integer> provinciaCodigo = HashBiMap.create();
    private final static BiMap<Integer, String> codigoProvincia = provinciaCodigo.inverse();
    private static final long serialVersionUID = -8103908310482470417L;
    protected final int codigoPostal;
    protected final String provincia;
    protected final String poblacion;

    public Direccion(final int codigoPostal, final String provincia, final String poblacion) {
        validate(provincia + " tiene multiples codigos postales", !provinciaCodigo.containsKey(provincia) || provinciaCodigo.get(provincia) == codigoPostal);
        validate(codigoPostal + " tiene multiples provincia", !codigoProvincia.containsKey(codigoPostal) || codigoProvincia.get(codigoPostal).equals(provincia));
        this.codigoPostal = numberNotNegative("Codigo postal", codigoPostal);
        this.provincia = stringNotEmpty("Provincia", provincia);
        this.poblacion = stringNotEmpty("Poblacion", poblacion);
        provinciaCodigo.put(provincia, codigoPostal);
    }

    final public int getCodigoPostal() {
        return codigoPostal;
    }

    final public String getProvincia() {
        return provincia;
    }

    final public String getPoblacion() {
        return poblacion;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Direccion direccion = (Direccion) o;
        return codigoPostal == direccion.codigoPostal &&
                Objects.equals(provincia, direccion.provincia) &&
                Objects.equals(poblacion, direccion.poblacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoPostal, provincia, poblacion);
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "codigoPostal=" + getCodigoPostal() +
                ", provincia='" + getProvincia() + '\'' +
                ", poblacion='" + getPoblacion() + '\'' +
                '}';
    }
}