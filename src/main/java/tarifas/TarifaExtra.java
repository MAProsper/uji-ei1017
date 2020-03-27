package tarifas;

import helpers.clases.Llamada;

import java.util.Objects;
import java.util.Optional;

import static helpers.estaticos.Arguments.referenceNotNull;

public abstract class TarifaExtra extends Tarifa {
    private static final long serialVersionUID = 3116940651730525924L;
    protected final Tarifa tarifa;

    public TarifaExtra(final Tarifa tarifa, final double precio) {
        super(precio);
        this.tarifa = referenceNotNull("Tarifa", tarifa);
    }

    final public Tarifa getTarifa() {
        return tarifa;
    }

    protected abstract Optional<Double> getImporteExtra(final Llamada llamada);

    @Override
    public double getImporte(final Llamada llamada) {
        final double base = tarifa.getImporte(llamada);
        final Optional<Double> extra = getImporteExtra(llamada);
        return extra.map(importe -> Math.min(base, importe)).orElse(base);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TarifaExtra that = (TarifaExtra) o;
        return Objects.equals(tarifa, that.tarifa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tarifa);
    }

    @Override
    public String toString() {
        return "TarifaExtra{" +
                "precio=" + precio +
                "tarifa=" + tarifa +
                '}';
    }
}
