package Helpers;

import Tarifas.Tarifa;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static Helpers.ValidatorArguments.referenceNotNull;

public class Servicio {
    final List<Llamada> llamadas;
    Tarifa tarifa;

    public Servicio(Tarifa tarifa) {
        setTarifa(tarifa);
        llamadas = new LinkedList<>();
    }

    final public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = referenceNotNull("tarifa", tarifa);
    }

    final public List<Llamada> getLlamadas() {
        return Collections.unmodifiableList(llamadas);
    }

    public void addLlamada(Llamada llamada) {
        llamadas.add(referenceNotNull("llamada", llamada));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servicio servicio = (Servicio) o;
        return Objects.equals(tarifa, servicio.tarifa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tarifa);
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "tarifa=" + tarifa +
                ", llamadas=" + llamadas +
                '}';
    }
}
