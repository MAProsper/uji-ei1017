package tarifas.generadores;

import tarifas.TarifaDomingo;

public class GeneradorTarifaDomingo extends GeneradorTarifaBase {
    public final TarifaDomingo nextTarifaDomingo() {
        return new TarifaDomingo(nextTarifa(), nextPrecio());
    }
}
