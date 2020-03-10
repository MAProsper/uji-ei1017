package app.ventanas;

import app.Formato;
import app.Textbox;
import clientes.Cliente;

import java.util.stream.Collectors;

import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaLlamadas extends Ventana {
    public VentanaLlamadas() {
        super(
                "Llamadas",
                "Gestion de llamadas",
                true, Textbox.empty(), Button.values());
    }

    @Override
    public void update() {
        final Cliente cliente = getGestor().getClienteSelecionado();
        setList(cliente.getLlamadas().stream().map(Formato::llamda).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(final app.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case ANYADIR_LLAMADA:
                ventana = new VentanaLlamadaNueva();
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    enum Button implements app.Button {
        ANYADIR_LLAMADA("Añadir llamada"),
        VOLVER("volver");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
