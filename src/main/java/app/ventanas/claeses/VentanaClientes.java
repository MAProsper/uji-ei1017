package app.ventanas.claeses;

import app.Formatter;
import app.Gestor;
import app.ventanas.abstractas.Ventana;
import clientes.Cliente;
import clientes.ClienteEmpresa;
import clientes.ClientePaticular;
import helpers.clases.Direccion;
import helpers.interfaces.Factory;
import helpers.interfaces.FactoryClientes;
import tarifas.Tarifa;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.*;

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
                ventana = new VentanaClienteNuevo((FactoryClientes) button);
                break;
            case NUEVO_PARTICULAR:
                ventana = new VentanaClienteParticularNuevo((FactoryClientes) button);
                break;
            case NUEVO_EMPRESA:
                ventana = new VentanaClienteEmpresaNuevo((FactoryClientes) button);
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

    public enum Button implements app.ventanas.interfaces.Button, FactoryClientes {
        VER_CLIENTE("Ver cliente"),
        NUEVO_CLIENTE("Nuevo cliente", Cliente.class),
        NUEVO_PARTICULAR("Nuevo particular", ClientePaticular.class),
        NUEVO_EMPRESA("Nuevo empresa", ClienteEmpresa.class),
        VOLVER("Volver");

        private final String description;
        private Class<? extends Cliente> clase;

        Button(final String description, final Class<? extends Cliente> clase) {
            this.description = stringNotEmpty("Descripcion", description);
            this.clase = clase;
        }

        Button(final String description) {
            this(description, null);
        }

        public String getDescription() {
            return description;
        }

        public Cliente getCliente(final String NIF, final String nombre, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa) {
            final Class<? extends Cliente> clase = referenceNotNull("Clase", this.clase);
            try {
                return clase.getConstructor(String.class, String.class, Direccion.class, String.class, LocalDateTime.class, Tarifa.class).newInstance(NIF, nombre, direccion, correo, fechaAlta, tarifa);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
                throw Factory.error(clase.getName());
            }
        }

        public Cliente getCliente(final String NIF, final String nombre, final String apellidos, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa) {
            final Class<? extends Cliente> clase = referenceNotNull("Clase", this.clase);
            try {
                return clase.getConstructor(String.class, String.class, String.class, Direccion.class, String.class, LocalDateTime.class, Tarifa.class).newInstance(NIF, nombre, apellidos, direccion, correo, fechaAlta, tarifa);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
                throw Factory.error(clase.getName());
            }
        }
    }
}
