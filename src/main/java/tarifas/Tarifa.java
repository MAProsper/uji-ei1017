package tarifas;

import helpers.clases.Llamada;

import java.io.Serializable;
import java.util.Objects;

import static helpers.estaticos.Arguments.numberNotNegative;
import static helpers.estaticos.Arguments.referenceNotNull;

public class Tarifa implements Serializable {

    private static final long serialVersionUID = -4829115908149179461L;
    protected final double precio;

    public Tarifa(final double precio) {
        this.precio = numberNotNegative("Precio", precio);
    }

    public double getPrecio() {
        return precio;
    }

    public double getImporte(final Llamada llamada) {
        referenceNotNull("Llamada", llamada);
        return llamada.getDuracion() * precio;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Tarifa tarifa1 = (Tarifa) o;
        return Double.compare(tarifa1.precio, precio) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(precio);
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "precio=" + precio +
                '}';
    }
}