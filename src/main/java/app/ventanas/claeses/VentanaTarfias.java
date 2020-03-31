package app.ventanas.claeses;

import app.Parser;
import app.ventanas.abstractas.Ventana;
import clientes.Cliente;
import tarifas.Tarifa;
import tarifas.TarifaDomingo;
import tarifas.TarifaExtra;
import tarifas.TarifaTarde;

import java.util.Optional;
import java.util.function.BiFunction;

import static helpers.estaticos.Arguments.*;

public class VentanaTarfias extends Ventana {
    protected final Cliente cliente;

    public VentanaTarfias(final Cliente cliente) {
        super("Tarifas", "Seleciona una tarifa", false, Textbox.values(), Button.values());
        this.cliente = referenceNotNull("Cliente", cliente);
    }

    @Override
    public Optional<Ventana> pressButton(final app.ventanas.interfaces.Button button) {
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Ventana ventana = null;

        if (button != Button.VOLVER) {
            final Button factoria = (Button) button;
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

    public enum Button implements app.ventanas.interfaces.Button {
        DOMINGO("Domingo", TarifaDomingo::new),
        TARDES("Tardes", TarifaTarde::new),
        VOLVER("Volver");

        private final String desciption;
        private final BiFunction<Tarifa, Double, TarifaExtra> action;

        Button(final String desciption, final BiFunction<Tarifa, Double, TarifaExtra> action) {
            this.desciption = stringNotEmpty("Desciption", desciption);
            this.action = action;
        }

        Button(final String desciption) {
            this(desciption, null);
        }

        public TarifaExtra getTarifa(final Tarifa tarifa, final double precio) {
            referenceNotNull("Tarifa", tarifa);
            return referenceNotNull("Action", action).apply(tarifa, precio);
        }

        @Override
        public String getDescription() {
            return desciption;
        }
    }
}
