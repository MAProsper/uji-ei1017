package app.ventanas.claeses;

import app.Parser;
import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.Ventana;
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
public class VentanaTarfias extends Ventana {
    protected final Cliente cliente;

    // Vista (define la vista contreta)
    public VentanaTarfias(final Cliente cliente) {
        super("Tarifas", "Seleciona una tarifa", Table.empty(), Textbox.values(), Button.values());
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

    public enum Button implements app.ventanas.interfaces.Button, FactoryTarifas {
        DOMINGO("Domingo", TarifaDomingo.class),
        TARDES("Tardes", TarifaTarde.class),
        VOLVER("Volver");

        private final String desciption;
        private final Class<? extends TarifaExtra> clase;

        Button(final String desciption, final Class<? extends TarifaExtra> clase) {
            this.desciption = stringNotEmpty("Desciption", desciption);
            this.clase = clase;
        }

        Button(final String desciption) {
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
    public Optional<Gestionable> pressButton(final app.ventanas.interfaces.Button button) { // Gestiona la acción del usuario
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Gestionable ventana = null;

        if (button != Button.VOLVER) {
            // Vista.getTextbox (2. solicita datos a la vista)
            final String precio = getTextbox(Textbox.PRECIO);

            final FactoryTarifas factoria = (FactoryTarifas) button;
            ventana = VentanaError.attempt(
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
