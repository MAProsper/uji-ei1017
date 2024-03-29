package app.helpers.estaticos;

import app.helpers.interfaces.Factory;
import app.helpers.interfaces.FactoryVistaRango;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.abstractas.VistaRango;
import app.ventanas.vistas.clases.VistaClientesRango;
import app.ventanas.vistas.clases.VistaFacturasRango;
import app.ventanas.vistas.clases.VistaLlamadasRango;
import com.google.common.collect.Range;
import helpers.interfaces.Description;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TipoRangoBuscar implements Description, FactoryVistaRango {
    CLIENTES("clientes", VistaClientesRango.class),
    FACTURAS("facturas", VistaFacturasRango.class),
    LLAMADAS("llamadas", VistaLlamadasRango.class);

    private final String description;
    private final Class<? extends VistaRango> clase;

    TipoRangoBuscar(final String description, final Class<? extends VistaRango> clase) {
        this.description = stringNotEmpty("Descripcion", description);
        this.clase = referenceNotNull("Clase", clase);
    }

    public String getDescription() {
        return description;
    }

    // Metodos para la fabrica de vistas
    public Vista getVista(final Range<LocalDateTime> periodo) {
        try {
            return clase.getConstructor(Range.class).newInstance(periodo);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
            throw Factory.error(clase.getName());
        }
    }
}