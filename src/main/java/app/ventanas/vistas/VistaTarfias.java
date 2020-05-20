package app.ventanas.vistas;

import app.helpers.Parser;
import app.ventanas.abstractas.Vista;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.interfaces.Table;
import clientes.Cliente;
import helpers.interfaces.Factory;
import helpers.interfaces.FactoryTarifas;
import tarifas.Tarifa;
import tarifas.TarifaDomingo;
import tarifas.TarifaExtra;
import tarifas.TarifaTarde;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VistaTarfias extends VistaPropia {
    protected final Cliente cliente;

    // Vista (define la vista contreta)
    public VistaTarfias(final Cliente cliente) {
        super("Tarifas", "Seleciona una tarifa", Table.empty(), Textbox.values(), Accion.values());
        this.cliente = referenceNotNull("Cliente", cliente);
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        PRECIO("Precio");

        private final String desciption;

        Textbox(String desciption) {
            this.desciption = stringNotEmpty("Desciption", desciption);
        }

        @Override
        public String getDescription() {
            return desciption;
        }
    }

    public enum Accion implements app.ventanas.interfaces.Accion, FactoryTarifas {
        DOMINGO("Domingo", TarifaDomingo.class),
        TARDES("Tardes", TarifaTarde.class),
        VOLVER("Volver");

        private final String desciption;
        private final Class<? extends TarifaExtra> clase;

        Accion(final String desciption, final Class<? extends TarifaExtra> clase) {
            this.desciption = stringNotEmpty("Desciption", desciption);
            this.clase = clase;
        }

        Accion(final String desciption) {
            this(desciption, null);
        }

        @Override
        public String getDescription() {
            return desciption;
        }

        // Metodos para la fabrica de tarifas
        @Override
        public TarifaExtra getTarifa(final Tarifa tarifa, final double precio) {
            final Class<? extends TarifaExtra> clase = referenceNotNull("Clase", this.clase);
            try {
                return clase.getConstructor(Tarifa.class, Double.TYPE).newInstance(tarifa, precio);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
                throw Factory.error(clase.getName());
            }
        }
    }

    // Controlador (define el controlador concreto)
    @Override
    public Optional<Vista> pressButton(final app.ventanas.interfaces.Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof Accion);
        Vista ventana = null;

        if (accion != Accion.VOLVER) {
            // Vista.getTextbox (2. solicita datos a la vista)
            final String precio = getTextbox(Textbox.PRECIO);

            final FactoryTarifas factoria = (FactoryTarifas) accion;
            ventana = VistaError.attempt(
                    () -> Parser.real("Precio", precio),
                    p -> {
                        // Cliente.setTarifa (3. actualiza el modelo)
                        cliente.setTarifa(factoria.getTarifa(cliente.getTarifa(), p));
                        return null;
                    }
            );
        }

        return Optional.ofNullable(ventana);
    }
}
