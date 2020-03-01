package Helpers;

import com.google.common.collect.Range;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Helpers.ValidatorArguments.validate;

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
}
