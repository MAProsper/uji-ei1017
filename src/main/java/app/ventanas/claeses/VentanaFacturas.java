package app.ventanas.claeses;

import app.Formatter;
import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;
import helpers.interfaces.Description;

import java.util.Optional;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VentanaFacturas extends Ventana {
    protected final Cliente cliente;

    // Vista (define la vista contreta)
    public VentanaFacturas(final Cliente cliente) {
        super(
                "Facturas",
                "Gestion de facturas",
                Table.values(), Textbox.empty(), Button.values());

        this.cliente = referenceNotNull("Cliente", cliente);
    }

    public enum Tipo implements Description {
        CLIENTES("clientes"),
        FACTURAS("facturas"),
        LLAMADAS("llamadas");

        private final String description;

        Tipo(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Table implements app.ventanas.interfaces.Table {
        CODIGO("Codigo"),
        FECHA("Fecha"),
        PERIODO("Periodo"),
        IMPORTE("Importe"),
        TARIFA("Tarifa (euro/min)");

        private final String description;

        Table(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Button implements app.ventanas.interfaces.Button {
        ANYADIR_FACTURA("Añadir factura"),
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
        setTable(cliente.getFacturas().stream().map(Formatter::format).collect(Collectors.toList()));
    }

    // Controlador (define el controlador concreto)
    @Override
    public Optional<Gestionable> pressButton(final app.ventanas.interfaces.Button button) { // Gestiona la acción del usuario
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Gestionable ventana = null;

        switch ((Button) button) {
            case ANYADIR_FACTURA:
                ventana = new VentanaFacturaNueva(cliente);
                break;
            case VOLVER:
                break;
            default:
                throw new ValidationException("Button no clasificado");
        }

        return Optional.ofNullable(ventana);
    }
}
