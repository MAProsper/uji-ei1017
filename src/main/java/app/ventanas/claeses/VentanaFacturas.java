package app.ventanas.claeses;

import app.Formato;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Description;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;

import java.util.Optional;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;

public class VentanaFacturas extends Ventana {
    protected final Cliente cliente;

    public VentanaFacturas(final Cliente cliente) {
        super(
                "Facturas",
                "Gestion de facturas",
                true, Textbox.empty(), Button.values());

        this.cliente = referenceNotNull("Cliente", cliente);
    }

    @Override
    protected void update() {
        setList(cliente.getFacturas().stream().map(Formato::factura).collect(Collectors.toList()));
    }

    @Override
    public Optional<Ventana> pressButton(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;

        switch ((Button) referenceNotNull("Button", button)) {
            case ANYADIR_FACTURA:
                ventana = new VentanaFacturaNueva(cliente);
                break;
            case VOLVER:
                break;
        }

        return Optional.ofNullable(ventana);
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
}
