package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonTarifas;
import app.componentes.textboxes.TextboxTarifas;
import app.helpers.estaticos.Parser;
import app.helpers.interfaces.FactoryTarifas;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaError;
import app.ventanas.vistas.clases.VistaTarfias;
import clientes.Cliente;

import static helpers.estaticos.Arguments.validate;

public class ControladorTarifas extends Controlador {
    public ControladorTarifas() {
        super();
    }

    @Override
    public void gestionaButton(final Button button) {
        validate("Button tiene que ser de este controlador", button instanceof ButtonTarifas);
        Vista vista = null;

        if (button != ButtonTarifas.VOLVER) {
            final VistaTarfias vistaActual = (VistaTarfias) getVista();
            final String precio = vistaActual.getTextbox(TextboxTarifas.PRECIO);
            final Cliente cliente = vistaActual.getCliente();

            final FactoryTarifas factoria = (FactoryTarifas) button;
            vista = VistaError.attempt(
                    () -> Parser.real("Precio", precio),
                    p -> {
                        cliente.setTarifa(factoria.getTarifa(cliente.getTarifa(), p));
                        getModelo().updateVistas();
                        return null;
                    }
            );
        }

        vistaNext(vista);
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaTarfias);
    }
}
