package app.ventanas.claeses;

import app.Parser;
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

public class VentanaTarfias extends Ventana {
    protected final Cliente cliente;

    public VentanaTarfias(final Cliente cliente) {
        super("Tarifas", "Seleciona una tarifa", Table.empty(), Textbox.values(), Button.values());
        this.cliente = referenceNotNull("Cliente", cliente);
    }

    @Override
    public Optional<Ventana> pressButton(final app.ventanas.interfaces.Button button) {
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Ventana ventana = null;

        if (button != Button.VOLVER) {
            final FactoryTarifas factoria = (FactoryTarifas) button;
            final String precio = getTextbox(Textbox.PRECIO);
            ventana = VentanaError.attempt(
                    () -> Parser.real("Precio", precio),
                    p -> {
                        cliente.setTarifa(factoria.getTarifa(cliente.getTarifa(), p));
                        return null;
                    }
            );
        }

        return Optional.ofNullable(ventana);
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
        public TarifaExtra getTarifa(final Tarifa tarifa, final double precio) {
            final Class<? extends TarifaExtra> clase = referenceNotNull("Clase", this.clase);
            try {
                return clase.getConstructor(Tarifa.class, Double.TYPE).newInstance(tarifa, precio);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
                throw Factory.error(clase.getName());
            }
        }

        @Override
        public String getDescription() {
            return desciption;
        }
    }
}
