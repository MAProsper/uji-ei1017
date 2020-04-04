package app.ventanas.claeses;

import app.Formatter;
import app.Gestor;
import app.ventanas.abstractas.Ventana;
import clientes.Cliente;
import clientes.ClienteEmpresa;
import clientes.ClientePaticular;
import helpers.clases.Direccion;
import helpers.estaticos.Arguments;
import tarifas.Tarifa;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.stringNotEmpty;
import static helpers.estaticos.Arguments.validate;

public class VentanaClientes extends Ventana {
    public VentanaClientes() {
        super(
                "Clientes",
                "Listado de clientes",
                true, Textbox.values(), Button.values());
    }

    @Override
    protected void update() {
        setList(getGestor().getClientes().stream().map(Formatter::format).collect(Collectors.toList()));
    }

    @Override
    public Optional<Ventana> pressButton(final app.ventanas.interfaces.Button button) {
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Ventana ventana = null;

        switch ((Button) button) {
            case VER_CLIENTE:
                final Gestor gestor = getGestor();
                final String NIF = getTextbox(Textbox.SELECIONADO_NIF);
                ventana = VentanaError.attempt(() -> gestor.buscarCliente(NIF), gestor::getVisor);
                break;
            case NUEVO_CLIENTE:
                ventana = new VentanaClienteNuevo((Button) button);
                break;
            case NUEVO_PARTICULAR:
                ventana = new VentanaClienteParticularNuevo((Button) button);
                break;
            case NUEVO_EMPRESA:
                ventana = new VentanaClienteEmpresaNuevo((Button) button);
                break;
            case VOLVER:
                break;
            default:
                validate("Button no clasificado", false);
        }

        return Optional.ofNullable(ventana);
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        SELECIONADO_NIF("Selecionado NIF");

        private final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Button implements app.ventanas.interfaces.Button {
        VER_CLIENTE("Ver cliente"),
        NUEVO_CLIENTE("Nuevo cliente", Cliente.class),
        NUEVO_PARTICULAR("Nuevo particular", ClientePaticular.class),
        NUEVO_EMPRESA("Nuevo empresa", ClienteEmpresa.class),
        VOLVER("Volver");

        private final String description;
        private Class<? extends Cliente> cliente;

        Button(final String description, final Class<? extends Cliente> cliente) {
            this.description = stringNotEmpty("Descripcion", description);
            this.cliente = cliente;
        }

        Button(final String description) {
            this(description, null);
        }

        public String getDescription() {
            return description;
        }

        public Cliente getCliente(final String NIF, final String nombre, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa) {
            try {
                return cliente.getConstructor(String.class, String.class, Direccion.class, String.class, LocalDateTime.class, Tarifa.class).newInstance(NIF, nombre, direccion, correo, fechaAlta, tarifa);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
                throw new Arguments.ValidationException("No se puede crear un cliente");
            }
        }

        public Cliente getClienteParticular(final String NIF, final String nombre, final String apellidos, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa) {
            try {
                return cliente.getConstructor(String.class, String.class, String.class, Direccion.class, String.class, LocalDateTime.class, Tarifa.class).newInstance(NIF, nombre, apellidos, direccion, correo, fechaAlta, tarifa);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
                throw new Arguments.ValidationException("No se puede crear un cliente particular");
            }
        }

        public Cliente getClienteEmpresa(final String NIF, final String nombre, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa) {
            try {
                return cliente.getConstructor(String.class, String.class, Direccion.class, String.class, LocalDateTime.class, Tarifa.class).newInstance(NIF, nombre, direccion, correo, fechaAlta, tarifa);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
                throw new Arguments.ValidationException("No se puede crear un cliente empresa");
            }
        }
    }
}
