package Helpers;

import java.util.*;

public class Generator {
    final static Random genBase = new Random();

    public Date nextFecha() {
        return new Date(genBase.nextLong());
    }
}
