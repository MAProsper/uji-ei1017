package app.ventanas.claeses;

import app.Formato;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;

import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;

public class VentanaLlamadas extends Ventana {
    final Cliente cliente;

    public VentanaLlamadas(final Cliente cliente) {
        super(
                "Llamadas",
                "Gestion de llamadas",
                true, Textbox.empty(), Button.values());

        this.cliente = referenceNotNull("cliente", cliente);
    }

    @Override
    protected void update() {
        setList(cliente.getLlamadas().stream().map(Formato::llamda).collect(Collectors.toList()));
    }

    @Override
    protected Ventana handle(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case NUEVA_LLAMADA:
                ventana = new VentanaLlamadaNueva(cliente);
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    private enum Button implements app.ventanas.interfaces.Button {
        NUEVA_LLAMADA("Añadir llamada"),
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
