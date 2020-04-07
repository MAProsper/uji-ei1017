package app.ventanas.interfaces;

import tarifas.Tarifa;

public interface FactoryTarifas extends Factory {
    Tarifa getTarifa(final Tarifa tarifa, final double precio);
}
