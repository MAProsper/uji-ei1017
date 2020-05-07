package app.ventanas.claeses;

import app.Formatter;
import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;

import java.util.Optional;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.*;

public class VentanaLlamadas extends Ventana {
    protected final Cliente cliente;

    public VentanaLlamadas(final Cliente cliente) {
        super(
                "Llamadas",
                "Gestion de llamadas",
                Table.values(), Textbox.empty(), Button.values());

        this.cliente = referenceNotNull("Cliente", cliente);
    }

    @Override
    protected void update() {
        setTable(cliente.getLlamadas().stream().map(Formatter::format).collect(Collectors.toList()));
    }

    @Override
    public Optional<Gestionable> pressButton(final app.ventanas.interfaces.Button button) {
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

    public final Cliente getCliente() {
        return cliente;
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
}
