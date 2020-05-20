package app.ventanas.claeses;

import app.Formatter;
import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;

import java.util.Optional;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VentanaLlamadas extends Ventana {
    protected final Cliente cliente;

    // Vista (define la vista contreta)
    public VentanaLlamadas(final Cliente cliente) {
        super(
                "Llamadas",
                "Gestion de llamadas",
                Table.values(), Textbox.empty(), Button.values());

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

    public enum Button implements app.ventanas.interfaces.Button {
        NUEVA_LLAMADA("Añadir llamada"),
        VOLVER("Volver");

        private final String description;

        Button(final String description) {
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
    public Optional<Gestionable> pressButton(final app.ventanas.interfaces.Button button) { // Gestiona la acción del usuario
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Gestionable ventana = null;

        switch ((Button) button) {
            case NUEVA_LLAMADA:
                ventana = new VentanaLlamadaNueva(cliente);
                break;
            case VOLVER:
                break;
            default:
                throw new ValidationException("Button no clasificado");
        }

        return Optional.ofNullable(ventana);
    }
}
