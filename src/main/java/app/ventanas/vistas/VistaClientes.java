package app.ventanas.vistas;

import app.Modelo;
import app.helpers.Formatter;
import app.ventanas.abstractas.Vista;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;
import clientes.ClienteEmpresa;
import clientes.ClientePaticular;
import helpers.clases.Direccion;
import helpers.interfaces.Factory;
import helpers.interfaces.FactoryClientes;
import tarifas.Tarifa;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VistaClientes extends VistaPropia {

    // Vista (define la vista contreta)
    public VistaClientes() {
        super(
                "Clientes",
                "Listado de clientes, selecione uno para verlo",
                Table.values(), Textbox.empty(), Accion.values());
    }

    public enum Table implements app.ventanas.interfaces.Table {
        NIF("NIF"),
        TIPO("Tipo"),
        NOMBRE("Nombre");

        private final String description;

        Table(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Accion implements app.ventanas.interfaces.Accion, FactoryClientes {
        VER_CLIENTE("Ver cliente"),
        NUEVO_CLIENTE("Nuevo cliente", Cliente.class),
        NUEVO_PARTICULAR("Nuevo particular", ClientePaticular.class),
        NUEVO_EMPRESA("Nuevo empresa", ClienteEmpresa.class),
        VOLVER("Volver");

        private final String description;
        private final Class<? extends Cliente> clase;

        Accion(final String description, final Class<? extends Cliente> clase) {
            this.description = stringNotEmpty("Descripcion", description);
            this.clase = clase;
        }

        Accion(final String description) {
            this(description, null);
        }

        public String getDescription() {
            return description;
        }

        // Metodos para la fabrica de clientes
        @Override
        public Cliente getCliente(final String NIF, final String nombre, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa) {
            final Class<? extends Cliente> clase = referenceNotNull("Clase", this.clase);
            try {
                return clase.getConstructor(String.class, String.class, Direccion.class, String.class, LocalDateTime.class, Tarifa.class).newInstance(NIF, nombre, direccion, correo, fechaAlta, tarifa);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
                throw Factory.error(clase.getName());
            }
        }

        @Override
        public Cliente getCliente(final String NIF, final String nombre, final String apellidos, final Direccion direccion, final String correo, final LocalDateTime fechaAlta, final Tarifa tarifa) {
            final Class<? extends Cliente> clase = referenceNotNull("Clase", this.clase);
            try {
                return clase.getConstructor(String.class, String.class, String.class, Direccion.class, String.class, LocalDateTime.class, Tarifa.class).newInstance(NIF, nombre, apellidos, direccion, correo, fechaAlta, tarifa);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
                throw Factory.error(clase.getName());
            }
        }
    }

    @Override
    protected void update() { // Gestiona la notificacion del modelo
        // Modelo.getClientes (5. solicita nuevos datos)
        setTable(getModelo().getClientes().stream().map(Formatter::format).collect(Collectors.toList()));
    }

    // Controlador (define el controlador concreto)
    @Override
    public Optional<Vista> pressButton(final app.ventanas.interfaces.Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof Accion);
        Vista ventana = null;

        switch ((Accion) accion) {
            case VER_CLIENTE:
                // Vista.getTextbox (2. solicita datos a la vista)
                final Optional<List<String>> selection = getSelectedRow();

                if (selection.isPresent()) {
                    final List<String> row = selection.get();
                    final String NIF = row.get(Table.NIF.ordinal());

                    // Modelo.buscarCliente (3. consulta datos del modelo)
                    final Modelo modelo = getModelo();
                    ventana = modelo.getVisor(modelo.buscarCliente(NIF));
                } else {
                    ventana = new VistaError("No se ha selecionado ningun cliente");
                }
                break;
            case NUEVO_CLIENTE:
                ventana = new VistaClienteNuevo((FactoryClientes) accion);
                break;
            case NUEVO_PARTICULAR:
                ventana = new VistaClienteParticularNuevo((FactoryClientes) accion);
                break;
            case NUEVO_EMPRESA:
                ventana = new VistaClienteEmpresaNuevo((FactoryClientes) accion);
                break;
            case VOLVER:
                break;
            default:
                throw new ValidationException("Acción no clasificada");
        }

        return Optional.ofNullable(ventana);
    }
}
