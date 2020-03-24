package helpers.clases;

import com.google.common.collect.Range;
import helpers.interfaces.Cronologico;
import tarifas.Tarifa;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static helpers.estaticos.Arguments.*;

public class Factura implements Cronologico, Serializable {
    final static Set<Integer> codigos = new HashSet<>();
    private static final long serialVersionUID = 4248121609591730120L;
    protected final int codigo;
    protected final Tarifa tarifa;
    protected final LocalDateTime fechaEmision;
    protected final Range<LocalDateTime> periodo;
    protected final double importe;

    public Factura(final int codigo, final Tarifa tarifa, final LocalDateTime fechaEmision, final Range<LocalDateTime> periodo, final double importe) {
        validate("Codigo de factura repetido", !codigos.contains(codigo));
        this.codigo = numberNotNegative("Codigo", codigo);
        this.tarifa = referenceNotNull("Tarifa", tarifa);
        this.fechaEmision = referenceNotNull("Fecha", fechaEmision);
        this.periodo = referenceNotNull("Periodo", periodo);
        this.importe = numberNotNegative("Importe", importe);
        codigos.add(codigo);
    }

    final public int getCodigo() {
        return codigo;
    }

    final public Tarifa getTarifa() {
        return tarifa;
    }

    final public LocalDateTime getFecha() {
        return fechaEmision;
    }

    final public Range<LocalDateTime> getPeriodo() {
        return periodo;
    }

    final public double getImporte() {
        return importe;
    }

    @Override
    final public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return codigo == factura.codigo;
    }

    @Override
    final public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Factura{" +
                "codigo=" + codigo +
                ", tarifa=" + tarifa +
                ", fechaEmision=" + fechaEmision +
                ", periodo=" + periodo +
                ", importe=" + importe +
                '}';
    }
}
