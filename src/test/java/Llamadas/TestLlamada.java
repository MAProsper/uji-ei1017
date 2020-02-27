package Llamadas;

import Llamadas.Generadores.GeneradorLlamada;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TestLlamada {
    static final GeneradorLlamada genLlamada = new GeneradorLlamada();

    @RepeatedTest(3)
    public void testLlamada() {
        final String telefono = genLlamada.nextTelefono();
        final Date fecha = genLlamada.nextFecha();
        final double duracion = genLlamada.nextDuracion();
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
