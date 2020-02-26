package Tarifas;

import java.util.*;

import static Helpers.HelperArgument.*;

public class Tarifa {

    final double precio;

    public Tarifa(final double precio) {
        this.precio = numberNotNegative("precio", precio);
    }

    public double getPrecio() {
        return precio;
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