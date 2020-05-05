package tarifas;

import helpers.clases.Llamada;

import java.time.DayOfWeek;
import java.util.Optional;

public class TarifaDomingo extends TarifaExtra {

    private static final long serialVersionUID = 8543989365499662460L;

    public TarifaDomingo(final Tarifa tarifa, final double precio) {
        super(tarifa, precio);
    }

    @Override
    protected Optional<Double> getImporteExtra(final Llamada llamada) {
        return llamada.getFecha().getDayOfWeek() == DayOfWeek.SUNDAY ? Optional.of(llamada.getDuracion() * precio) : Optional.empty();
    }

    @Override
    public String toString() {
        return "TarifaDomingo{" +
                "tarifa=" + tarifa +
                ", precio=" + precio +
                '}';
    }
}
