package app.ventanas.vistas;

import app.helpers.Formatter;
import app.ventanas.abstractas.Vista;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;

import java.util.Optional;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VistaLlamadas extends VistaPropia {
    protected final Cliente cliente;

    // Vista (define la vista contreta)
    public VistaLlamadas(final Cliente cliente) {
        super(
                "Llamadas",
                "Gestion de llamadas",
                Table.values(), Textbox.empty(), Accion.values());

        this.cliente = referenceNotNull("Cliente", cliente);
    }

    public enum Table implements app.ventanas.interfaces.Table {
        FECHA("Fecha"),
        TELEFONO("Telefono"),
        DURACION("Duracion");

        private final String description;

        Table(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Accion implements app.ventanas.interfaces.Accion {
        NUEVA_LLAMADA("Añadir llamada"),
        VOLVER("Volver");

        private final String description;

        Accion(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    @Override
    protected void update() { // Gestiona la notificacion del modelo
        // Cliente.getLlamadas (5. solicita nuevos datos)
        setTable(cliente.getLlamadas().stream().map(Formatter::format).collect(Collectors.toList()));
    }

    public final Cliente getCliente() {
        return cliente;
    }

    // Controlador (define el controlador concreto)
    @Override
    public Optional<Vista> pressButton(final app.ventanas.interfaces.Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof Accion);
        Vista ventana = null;

        switch ((Accion) accion) {
            case NUEVA_LLAMADA:
                ventana = new VistaLlamadaNueva(cliente);
                break;
            case VOLVER:
                break;
            default:
                throw new ValidationException("Acción no clasificada");
        }

        return Optional.ofNullable(ventana);
    }
}
