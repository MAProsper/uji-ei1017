package Llamadas;

import java.util.*;

import static Helpers.HelperArgument.*;

public class Llamada {
    final String telefono;
    final Date fecha;
    final double duracion;

    public Llamada(final String telefono, final Date fecha, final double duracion) {
        this.telefono = stringMatchesPattern("telefono", telefono, "\\d{9}");
        this.fecha = referenceNotNull("fecha", fecha);
        this.duracion = numberNotNegative("duracion", duracion);
    }

    public String getTelefono() {
        return telefono;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getDuracion() {
        return duracion;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Llamada llamada = (Llamada) o;
        return Double.compare(llamada.duracion, duracion) == 0 &&
                Objects.equals(telefono, llamada.telefono) &&
                Objects.equals(fecha, llamada.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefono, fecha, duracion);
    }

    @Override
    public String toString() {
        return "Llamada{" +
                "telefono='" + telefono + '\'' +
                ", fecha=" + fecha +
                ", duracion=" + duracion +
                '}';
    }
}
