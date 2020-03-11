package helpers.clases;

import helpers.interfaces.Cronologico;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import static helpers.estaticos.Arguments.*;

public class Llamada implements Cronologico, Serializable {
    private static final long serialVersionUID = 9196325539442745850L;
    final String telefono;
    final LocalDate fecha;
    final double duracion;

    public Llamada(final String telefono, final LocalDate fecha, final double duracion) {
        this.telefono = stringMatchesPattern("telefono", telefono, "\\d{9}");
        this.fecha = referenceNotNull("fecha", fecha);
        this.duracion = numberNotNegative("duracion", duracion);
    }

    final public String getTelefono() {
        return telefono;
    }

    final public LocalDate getFecha() {
        return fecha;
    }

    final public double getDuracion() {
        return duracion;
    }

    @Override
    final public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Llamada llamada = (Llamada) o;
        return Double.compare(llamada.duracion, duracion) == 0 &&
                Objects.equals(telefono, llamada.telefono) &&
                Objects.equals(fecha, llamada.fecha);
    }

    @Override
    final public int hashCode() {
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
