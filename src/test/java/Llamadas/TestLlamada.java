package Llamadas;

import Helpers.HelperGenerator;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestLlamada {
    static final Random random = new Random();
    static final HelperGenerator generator = new HelperGenerator();

    @RepeatedTest(5)
    public void testLlamada() {
        final String telefono = generator.getTelefono();
        final Date fecha = generator.getFecha();
        final double duracion = random.nextDouble() * 999;
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
