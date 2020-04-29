package tarifas;

import com.google.common.collect.Range;
import helpers.clases.Llamada;

import java.util.Optional;

public class TarifaTarde extends TarifaExtra {
    private static final long serialVersionUID = -1767886901449819375L;
    protected static final Range<Integer> horario = Range.closedOpen(16, 20);

    public TarifaTarde(final Tarifa tarifa, final double precio) {
        super(tarifa, precio);
    }

    @Override
    protected Optional<Double> getImporteExtra(final Llamada llamada) {
        return horario.contains(llamada.getFecha().getHour()) ? Optional.of(precio) : Optional.empty();
    }

    public Range<Integer> getHorario() {
        return horario;
    }

    @Override
    public String toString() {
        return "TarifaTarde{" +
                "horario=" + horario +
                ", tarifa=" + tarifa +
                ", precio=" + precio +
                '}';
    }
}
