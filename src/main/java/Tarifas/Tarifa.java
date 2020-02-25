package Tarifas;

import java.util.*;

import static Helpers.HelperArgument.*;

public class Tarifa {

    final double tarifa;

    public Tarifa(final double tarifa) {
        this.tarifa = numberNotNegative("tarifa", tarifa);
    }

    public double getTarifa() {
        return tarifa;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Tarifa tarifa1 = (Tarifa) o;
        return Double.compare(tarifa1.tarifa, tarifa) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tarifa);
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "tarifa=" + tarifa +
                '}';
    }
}