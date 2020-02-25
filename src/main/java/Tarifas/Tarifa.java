package Tarifas;

import static Helpers.HelperArgument.numberNotNegative;

public class Tarifa {

    double tarifa;

    public Tarifa(double tarifa) {
        this.tarifa = numberNotNegative("Tarifa", tarifa);
    }

    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

    public double getTarifa() {
        return tarifa;
    }

}