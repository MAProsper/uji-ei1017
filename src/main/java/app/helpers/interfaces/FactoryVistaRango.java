package app.helpers.interfaces;

import app.ventanas.vistas.abstractas.Vista;
import com.google.common.collect.Range;

import java.time.LocalDateTime;

public interface FactoryVistaRango extends Factory {
    Vista getVista(final Range<LocalDateTime> periodo);
}
