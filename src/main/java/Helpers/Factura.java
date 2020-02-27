package Helpers;

import java.util.*;

import Tarifas.Tarifa;
import com.google.common.collect.*;

import static Helpers.ValidatorArguments.*;

public class Factura {
    final int codigo;
    final Tarifa tarifa;
    final Date fechaEmision;
    final Range<Date> periodo;

    final static Set<Integer> codigos = new HashSet<>();

    public Factura(final int codigo, final Tarifa tarifa, final Date fechaEmision, final Range<Date> periodo) {
        validate("codigo de factura repetido", !codigos.contains(codigo));
        this.codigo = numberNotNegative("codigo", codigo);
        this.tarifa = referenceNotNull("tarifa", tarifa);
        this.fechaEmision = referenceNotNull("fecha", fechaEmision);
        this.periodo = referenceNotNull("periodo", periodo);
        codigos.add(codigo);
    }

    public int getCodigo() {
        return codigo;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public Date getFecha() {
        return fechaEmision;
    }

    public Range<Date> getPeriodo() {
        return periodo;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Factura factura = (Factura) o;
        return codigo == factura.codigo &&
                Objects.equals(tarifa, factura.tarifa) &&
                Objects.equals(fechaEmision, factura.fechaEmision) &&
                Objects.equals(periodo, factura.periodo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, tarifa, fechaEmision, periodo);
    }

    @Override
    public String toString() {
        return "Factura{" +
                "codigo=" + codigo +
                ", tarifa=" + tarifa +
                ", fechaEmision=" + fechaEmision +
                ", periodo='" + periodo + '\'' +
                '}';
    }
}
