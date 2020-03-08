package helpers.generadores;

import java.util.Date;
import java.util.Random;

public class Generator {
    final static Random genBase = new Random();

    public Date nextFecha() {
        return new Date(genBase.nextLong());
    }
}
