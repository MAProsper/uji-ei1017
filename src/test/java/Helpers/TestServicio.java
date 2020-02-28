package Helpers;

import Helpers.Generadores.GeneradorServicio;
import Tarifas.Tarifa;
import org.junit.jupiter.api.RepeatedTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServicio {
    final GeneradorServicio generador = new GeneradorServicio();

    @RepeatedTest(3)
    public void testServicio() {
        final Tarifa tarifa = generador.nextTarifa();
        final List<Llamada> llamadas = new LinkedList<>();
        System.out.println("Servicio{" +
                "tarifa=" + tarifa +
                ", llamadas=" + llamadas +
                '}');
        final Servicio servicio = new Servicio(tarifa);
        assertEquals(tarifa, servicio.getTarifa());
        assertEquals(llamadas, servicio.getLlamadas());
    }

    @RepeatedTest(3)
    public void testClienteSetTarifa() {
        Servicio servicio = generador.nextServicio();
        final Tarifa tarifa = generador.nextTarifa();
        System.out.println(servicio + "\n" + tarifa);
        servicio.setTarifa(tarifa);
        assertEquals(tarifa, servicio.getTarifa());
    }

    @RepeatedTest(3)
    public void testClienteAddLlamada() {
        Servicio servicio = generador.nextServicio();
        final Llamada llamada = generador.nextLlamada();
        final List<Llamada> llamadas = new LinkedList<>();
        llamadas.add(llamada);
        System.out.println(servicio + "\n" + llamada);
        servicio.addLlamada(llamada);
        assertEquals(llamadas, servicio.getLlamadas());
    }
}
