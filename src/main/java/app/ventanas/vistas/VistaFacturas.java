package app.ventanas.vistas;

import app.helpers.Formatter;
import app.ventanas.abstractas.Vista;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;
import helpers.interfaces.Description;

import java.util.Optional;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VistaFacturas extends VistaPropia {
    protected final Cliente cliente;

    // Vista (define la vista contreta)
    public VistaFacturas(final Cliente cliente) {
        super(
                "Facturas",
                "Gestion de facturas",
                Table.values(), Textbox.empty(), Accion.values());

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

    public enum Accion implements app.ventanas.interfaces.Accion {
        ANYADIR_FACTURA("Añadir factura"),
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
        // Cliente.getFacturas (5. solicita nuevos datos)
        setTable(cliente.getFacturas().stream().map(Formatter::format).collect(Collectors.toList()));
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
            case ANYADIR_FACTURA:
                ventana = new VistaFacturaNueva(cliente);
                break;
            case VOLVER:
                break;
            default:
                throw new ValidationException("Acción no clasificada");
        }

        return Optional.ofNullable(ventana);
    }
}
