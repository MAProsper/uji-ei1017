package app.ventanas.claeses;

import app.Formatter;
import app.Gestor;
import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;
import helpers.clases.Direccion;

import java.util.Optional;
import java.util.function.BiFunction;

import static helpers.estaticos.Arguments.*;

public class VentanaCliente extends Ventana {
    protected final Cliente cliente;

    public VentanaCliente(final Cliente cliente) {
        super(
                "Cliente",
                "Gestion del cliente",
                Table.values(), Textbox.empty(), Button.values());

        this.cliente = referenceNotNull("Cliente", cliente);
    }

    @Override
    protected void update() {
        final Direccion direccion = cliente.getDireccion();

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

    public Optional<Gestionable> pressButton(final app.ventanas.interfaces.Button button) {
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
                getGestor().removeCliente(cliente);
                break;
            case VOLVER:
                break;
            default:
                throw new ValidationException("Button no clasificado");
        }

        return Optional.ofNullable(ventana);
    }

    public final Cliente getCliente() {
        return cliente;
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

    public enum Button implements app.ventanas.interfaces.Button { //Pruebas de como eliminar el switch (no se usa)
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
