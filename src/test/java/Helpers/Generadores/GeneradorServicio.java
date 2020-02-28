package Helpers.Generadores;

import Helpers.Llamada;
import Helpers.Servicio;
import Tarifas.Generadores.GeneradorTarifa;
import Tarifas.Tarifa;

public class GeneradorServicio {
    final static GeneradorTarifa genTarifa = new GeneradorTarifa();
    final static GeneradorLlamada genLlamada = new GeneradorLlamada();

    public Tarifa nextTarifa() {
        return genTarifa.nextTarifa();
    }

    public Servicio nextServicio() {
        return new Servicio(nextTarifa());
    }

    public Llamada nextLlamada() {
        return genLlamada.nextLlamada();
    }
}
