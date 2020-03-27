package app.ventanas.claeses;

import app.Formatter;
import app.Gestor;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;
import helpers.clases.Direccion;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

import static helpers.estaticos.Arguments.*;

public class VentanaCliente extends Ventana {
    protected final Cliente cliente;

    public VentanaCliente(final Cliente cliente) {
        super(
                "Cliente",
                "Gestion del cliente",
                true, Textbox.empty(), Button.values());

        this.cliente = referenceNotNull("Cliente", cliente);
    }

    @Override
    protected void update() {
        final Direccion direccion = cliente.getDireccion();

        setList(Arrays.asList(
                "NIF: " + cliente.getNIF(),
                "Nombre: " + cliente.getNombre(),
                "Codigo postal: " + direccion.getCodigoPostal(),
                "Provincia: " + direccion.getProvincia(),
                "Poblacion: " + direccion.getPoblacion(),
                "Correo electronico : " + cliente.getCorreo(),
                "Fecha de alta: " + Formatter.format(cliente.getFecha()),
                "Tarifa: " + cliente.getTarifa()
        ));
    }

    public Optional<Ventana> pressButton(final app.ventanas.interfaces.Button button) {
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Ventana ventana = null;

        switch ((Button) button) {
            case VER_LLAMADAS:
                ventana = new VentanaLlamadas(cliente);
                break;
            case VER_FACTURAS:
                ventana = new VentanaFacturas(cliente);
                break;
            case ANYADIR_TARIFAS:
                ventana = new VentanaTarfias(cliente);
                break;
            case BORRAR_CLIENTE:
                getGestor().removeCliente(cliente);
                break;
            case VOLVER:
                break;
            default:
                validate("Button no clasificado", false);
        }

        return Optional.ofNullable(ventana);
    }

    public final Cliente getCliente() {
        return cliente;
    }

    public enum Button implements app.ventanas.interfaces.Button {
        VER_LLAMADAS("Ver llamadas", (gestor, ventanaCliente) -> Optional.of(new VentanaLlamadas(ventanaCliente.getCliente()))),
        VER_FACTURAS("Ver facturas", (gestor, ventanaCliente) -> Optional.of(new VentanaFacturas(ventanaCliente.getCliente()))),
        ANYADIR_TARIFAS("Añadir tarifas", (gestor, ventanaCliente) -> Optional.of(new VentanaTarfias(ventanaCliente.getCliente()))),
        BORRAR_CLIENTE("Borrar cliente", (gestor, ventanaCliente) -> {
            gestor.removeCliente(ventanaCliente.getCliente());
            return Optional.empty();
        }),
        VOLVER("Volver", (gestor, ventanaCliente) -> Optional.empty());

        private final String description;
        private final BiFunction<Gestor, VentanaCliente, Optional<Ventana>> action;

        Button(final String description, final BiFunction<Gestor, VentanaCliente, Optional<Ventana>> action) {
            this.description = stringNotEmpty("Descripcion", description);
            this.action = referenceNotNull("Action", action);
        }

        public String getDescription() {
            return description;
        }

        public Optional<Ventana> handle(final Gestor gestor, final Ventana ventana) {
            referenceNotNull("Gestor", gestor);
            validate("La ventana no tiene relacion con este boton", ventana instanceof VentanaCliente);
            return action.apply(gestor, (VentanaCliente) ventana);
        }
    }
}
