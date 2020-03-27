package tarifas;

import com.google.common.collect.Range;
import helpers.clases.Llamada;

import java.time.DayOfWeek;
import java.util.Optional;

public class TarifaDomingo extends TarifaExtra {

    private static final long serialVersionUID = 8543989365499662460L;

    public TarifaDomingo(Tarifa tarifa, double precio) {
        super(tarifa, precio);
    }

    @Override
    protected Optional<Double> getImporteExtra(Llamada llamada) {
        return llamada.getFecha().getDayOfWeek() == DayOfWeek.SUNDAY ? Optional.of(precio) : Optional.empty();
    }
}
