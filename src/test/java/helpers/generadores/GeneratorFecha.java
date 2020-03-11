package helpers.generadores;

import java.time.LocalDate;
import java.util.Random;

public class GeneratorFecha {
    protected final static Random genBase = new Random();

    public final LocalDate nextFecha() {
        return LocalDate.ofEpochDay(genBase.nextLong() / 86400000L);
    }
}
