package helpers;

import com.google.common.collect.Range;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static helpers.ValidatorArguments.validate;

final public class Fecha {
    final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Fecha() {
    }

    public static Range<Date> getPeriodo(final Date inicio, final Date fin) {
        validate("final debe ser posterior a inicio", fin.compareTo(inicio) > 0);
        return Range.closedOpen(inicio, fin);
    }

    public static Date parseDate(final String date) throws ParseException {
        return dateFormat.parse(date);
    }

    public static <T extends Cronologico> List<T> filterRange(List<T> lista, Range<Date> periodo) {
        List<T> filtered = new LinkedList<>();
        for (T item : lista) if (periodo.contains(item.getFecha())) filtered.add(item);
        return filtered;
    }
}
