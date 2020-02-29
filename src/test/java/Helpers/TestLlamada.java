package Helpers;

import Helpers.Generadores.GeneradorLlamada;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLlamada {
    static final GeneradorLlamada generador = new GeneradorLlamada();

    @RepeatedTest(3)
    public void testLlamada() {
        final String telefono = generador.nextTelefono();
        final Date fecha = generador.nextFecha();
        final double duracion = generador.nextDuracion();
        System.out.println("Llamada{" +
                "telefono='" + telefono + '\'' +
                ", fecha=" + fecha +
                ", duracion=" + duracion +
                '}');
        final Llamada llamada = new Llamada(telefono, fecha, duracion);
        assertEquals(telefono, llamada.getTelefono());
        assertEquals(fecha, llamada.getFecha());
        assertEquals(duracion, llamada.getDuracion());
    }
}