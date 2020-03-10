package tarifas;

import helpers.clases.Llamada;

import java.io.Serializable;
import java.util.Objects;

import static helpers.estaticos.ValidatorArguments.numberNotNegative;
import static helpers.estaticos.ValidatorArguments.referenceNotNull;

public class Tarifa implements Serializable {

    private static final long serialVersionUID = -4829115908149179461L;
    final double precio;

    public Tarifa(final double precio) {
        this.precio = numberNotNegative("precio", precio);
    }

    public double getPrecio() {
        return precio;
    }

    public double getImporte(Llamada llamada) {
        referenceNotNull("llamada", llamada);
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