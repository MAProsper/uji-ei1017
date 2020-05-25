package helpers.clases;

import helpers.interfaces.Cronologico;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import static helpers.estaticos.Arguments.*;

public class Llamada implements Cronologico, Serializable {
    private static final long serialVersionUID = 9196325539442745850L;
    protected final String telefono;
    protected final LocalDateTime fecha;
    protected final double duracion;

    public Llamada(final String telefono, final LocalDateTime fecha, final double duracion) {
        this.telefono = stringMatchesPattern("Telefono", telefono, "\\d{9}");
        this.fecha = referenceNotNull("Fecha", fecha);
        this.duracion = numberNotNegative("Duracion", duracion);
    }

    final public String getTelefono() {
        return telefono;
    }

    final public LocalDateTime getFecha() {
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
        return Objects.equals(telefono, llamada.telefono) &&
                Objects.equals(fecha, llamada.fecha);
    }

    @Override
    final public int hashCode() {
        return Objects.hash(telefono, fecha);
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
