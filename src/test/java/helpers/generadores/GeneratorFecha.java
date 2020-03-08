package helpers.generadores;

import java.time.LocalDate;
import java.util.Random;

public class GeneratorFecha {
    final static Random genBase = new Random();

    public LocalDate nextFecha() {
        return LocalDate.ofEpochDay(genBase.nextLong());
    }
}
