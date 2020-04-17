package tarifas.generadores;

import tarifas.TarifaDomingo;

public class GeneradorTarifaDomingo extends GeneradorTarifa {
    public final TarifaDomingo nextTarifaDomingo() {
        return new TarifaDomingo(nextTarifa(), nextPrecio());
    }
}
