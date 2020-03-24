package helpers.generadores;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.TimeZone;

public class GeneratorFecha {
    protected final static Random genBase = new Random();

    public final LocalDateTime nextFecha() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(genBase.nextLong()), TimeZone.getDefault().toZoneId());
    }
}
