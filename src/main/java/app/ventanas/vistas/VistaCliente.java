package app.ventanas.vistas;

import app.helpers.Formatter;
import app.ventanas.abstractas.Vista;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;
import helpers.clases.Direccion;

import java.util.Optional;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VistaCliente extends VistaPropia {
    protected final Cliente cliente;

    // Vista (define la vista contreta)
    public VistaCliente(final Cliente cliente) {
        super(
                "Cliente",
                "Gestion del cliente",
                Table.values(), Textbox.empty(), Accion.values());

        this.cliente = referenceNotNull("Cliente", cliente);
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

    public enum Accion implements app.ventanas.interfaces.Accion {
        VER_LLAMADAS("Ver llamadas"),
        VER_FACTURAS("Ver facturas"),
        ANYADIR_TARIFAS("Añadir tarifas"),
        BORRAR_CLIENTE("Borrar cliente"),
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
    public Optional<Vista> pressButton(final app.ventanas.interfaces.Accion accion) { // Gestiona la accion del usuario
        validate("Acción tiene que ser esta ventana", accion instanceof Accion);
        Vista ventana = null;

        switch ((Accion) accion) {
            case VER_LLAMADAS:
                ventana = new VistaLlamadas(cliente);
                break;
            case VER_FACTURAS:
                ventana = new VistaFacturas(cliente);
                break;
            case ANYADIR_TARIFAS:
                ventana = new VistaTarfias(cliente);
                break;
            case BORRAR_CLIENTE:
                // Modelo.removeCliente (3. actualiza el modelo)
                getModelo().removeCliente(cliente);
                break;
            case VOLVER:
                break;
            default:
                throw new ValidationException("Acción no clasificada");
        }

        return Optional.ofNullable(ventana);
    }
}
