package app.ventanas;

import app.Formato;
import app.Textbox;
import clientes.Cliente;

import java.util.stream.Collectors;

import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaFacturas extends Ventana {
    public VentanaFacturas() {
        super(
                "Facturas",
                "Gestion de facturas",
                true, Textbox.empty(), Button.values());
    }

    @Override
    public void update() {
        final Cliente cliente = getGestor().getClienteSelecionado();
        setList(cliente.getFacturas().stream().map(Formato::factura).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(final app.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case ANYADIR_FACTURA:
                ventana = new VentanaFacturaNueva();
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    enum Button implements app.Button {
        ANYADIR_FACTURA("Buscar"),
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
