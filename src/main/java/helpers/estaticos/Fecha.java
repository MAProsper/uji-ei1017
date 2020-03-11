package helpers.estaticos;

import com.google.common.collect.Range;
import helpers.interfaces.Cronologico;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.validate;

final public class Fecha {
    private Fecha() {
    }

    public static Range<LocalDate> getPeriodo(final LocalDate inicio, final LocalDate fin) {
        validate("Final debe ser posterior a inicio", fin.compareTo(inicio) > 0);
        return Range.closedOpen(inicio, fin);
    }

    public static <T extends Cronologico> List<T> filterRange(final List<T> lista, final Range<LocalDate> periodo) {
        return lista.stream().filter(item -> periodo.contains(item.getFecha())).collect(Collectors.toList());
    }
}
