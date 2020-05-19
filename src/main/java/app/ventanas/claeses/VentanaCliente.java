package app.ventanas.claeses;

import app.Formatter;
import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;
import helpers.clases.Direccion;

import java.util.Optional;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VentanaCliente extends Ventana {
    protected final Cliente cliente;

    // Vista (define la vista contreta)
    public VentanaCliente(final Cliente cliente) {
        super(
                "Cliente",
                "Gestion del cliente",
                Table.values(), Textbox.empty(), Button.values());

        this.cliente = referenceNotNull("Cliente", cliente);
    }

    @Override
    protected void update() { // Gestiona la notificacion del modelo
        final Direccion direccion = cliente.getDireccion();

        // Cliente.get (5. solicita nuevos datos)
        setTable(new String[][]{
                {"NIF", cliente.getNIF()},
                {"Nombre", cliente.getNombre()},
                {"Codigo postal", Integer.toString(direccion.getCodigoPostal())},
                {"Provincia", direccion.getProvincia()},
                {"Poblacion", direccion.getPoblacion()},
                {"Correo electronico", cliente.getCorreo()},
                {"Fecha de alta", Formatter.format(cliente.getFecha())},
                {"Tarifa", cliente.getTarifa().toString()}
        });
    }

    public final Cliente getCliente() {
        return cliente;
    }

    // Controlador (define el controlador concreto)
    public Optional<Gestionable> pressButton(final app.ventanas.interfaces.Button button) { // Gestiona la accion del usuario
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Gestionable ventana = null;

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
                // Modelo.removeCliente (3. actualiza el modelo)
                getGestor().removeCliente(cliente);
                break;
            case VOLVER:
                break;
            default:
                throw new ValidationException("Button no clasificado");
        }

        return Optional.ofNullable(ventana);
    }

    public enum Table implements app.ventanas.interfaces.Table {
        ATRIBUTO("Atributo"),
        VALOR("Valor");

        private final String description;

        Table(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Button implements app.ventanas.interfaces.Button {
        VER_LLAMADAS("Ver llamadas"),
        VER_FACTURAS("Ver facturas"),
        ANYADIR_TARIFAS("Añadir tarifas"),
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
