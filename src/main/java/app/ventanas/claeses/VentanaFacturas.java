package app.ventanas.claeses;

import app.Formato;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;
import helpers.interfaces.Description;

import java.util.stream.Collectors;

import static helpers.estaticos.ValidatorArguments.referenceNotNull;
import static helpers.estaticos.ValidatorArguments.stringNotEmpty;

public class VentanaFacturas extends Ventana {
    private final Cliente cliente;

    public VentanaFacturas(final Cliente cliente) {
        super(
                "Facturas",
                "Gestion de facturas",
                true, Textbox.empty(), Button.values());

        this.cliente = referenceNotNull("cliente", cliente);
    }

    @Override
    protected void update() {
        setList(cliente.getFacturas().stream().map(Formato::factura).collect(Collectors.toList()));
    }

    @Override
    protected Ventana handle(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case ANYADIR_FACTURA:
                ventana = new VentanaFacturaNueva(cliente);
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    public enum Tipo implements Description {
        CLIENTES("clientes"),
        FACTURAS("facturas"),
        LLAMADAS("llamadas");

        final String description;

        Tipo(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    private enum Button implements app.ventanas.interfaces.Button {
        ANYADIR_FACTURA("Añadir factura"),
        VOLVER("Volver");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
