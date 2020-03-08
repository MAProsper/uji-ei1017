package Helpers;

import Tarifas.Tarifa;
import com.google.common.collect.Range;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static Helpers.ValidatorArguments.*;

public class Factura implements Cronologico {
    final static Set<Integer> codigos = new HashSet<>();
    final int codigo;
    final Tarifa tarifa;
    final Date fechaEmision;
    final Range<Date> periodo;
    final double importe;

    public Factura(final int codigo, final Tarifa tarifa, final Date fechaEmision, final Range<Date> periodo, final double importe) {
        validate("codigo de factura repetido", !codigos.contains(codigo));
        this.codigo = numberNotNegative("codigo", codigo);
        this.tarifa = referenceNotNull("tarifa", tarifa);
        this.fechaEmision = referenceNotNull("fecha", fechaEmision);
        this.periodo = referenceNotNull("periodo", periodo);
        this.importe = numberNotNegative("importe", importe);
        codigos.add(codigo);
    }

    final public int getCodigo() {
        return codigo;
    }

    final public Tarifa getTarifa() {
        return tarifa;
    }

    final public Date getFecha() {
        return fechaEmision;
    }

    final public Range<Date> getPeriodo() {
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
