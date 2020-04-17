package tarifas.generadores;

import tarifas.TarifaTarde;

public class GeneradorTarifaTarde extends GeneradorTarifa {
    public final TarifaTarde nextTarifaTarde() {
        return new TarifaTarde(nextTarifa(), nextPrecio());
    }
}
