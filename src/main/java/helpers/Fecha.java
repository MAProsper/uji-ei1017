package helpers;

import com.google.common.collect.Range;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static helpers.ValidatorArguments.validate;

final public class Fecha {
    private Fecha() {
    }

    public static Range<LocalDate> getPeriodo(final LocalDate inicio, final LocalDate fin) {
        validate("final debe ser posterior a inicio", fin.compareTo(inicio) > 0);
        return Range.closedOpen(inicio, fin);
    }

    public static LocalDate parse(final String date) throws ParseException {
        return LocalDate.parse(date);
    }

    public static <T extends Cronologico> List<T> filterRange(List<T> lista, Range<LocalDate> periodo) {
        List<T> filtered = new LinkedList<>();
        for (T item : lista) if (periodo.contains(item.getFecha())) filtered.add(item);
        return filtered;
    }
}
