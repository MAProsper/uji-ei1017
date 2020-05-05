package tarifas.generadores;

import tarifas.TarifaTarde;

public class GeneradorTarifaTarde extends GeneradorTarifaBase {
    public final TarifaTarde nextTarifaTarde() {
        return new TarifaTarde(nextTarifa(), nextPrecio());
    }
}
