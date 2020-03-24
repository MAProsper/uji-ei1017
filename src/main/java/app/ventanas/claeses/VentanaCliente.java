package app.ventanas.claeses;

import app.Formatter;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;
import helpers.clases.Direccion;

import java.util.Arrays;
import java.util.Optional;

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
                "Tarifa base: " + cliente.getTarifa().getPrecio()
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
        VER_LLAMADAS("Ver llamadas"),
        VER_FACTURAS("Ver facturas"),
        BORRAR_CLIENTE("Borrar cliente"),
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
