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
import tarifas.Tarifa;

public class ControladorTarifas extends Controlador {
    public ControladorTarifas() {
        super();
    }

    @Override
    public boolean validateButton(final Button button) {
        return button instanceof ButtonTarifas;
    }

    @Override
    public void gestionaButton(final Button button) {
        super.gestionaButton(button);
        Vista vista = null;

        if (button != ButtonTarifas.VOLVER) {
            final VistaTarfias vistaActual = (VistaTarfias) getVista();
            final String precio = vistaActual.getTextbox(TextboxTarifas.PRECIO);
            final Cliente cliente = vistaActual.getCliente();

            final FactoryTarifas factoria = (FactoryTarifas) button;
            vista = VistaError.attempt(
                    () -> Parser.real("Precio", precio),
                    p -> {
                        final Tarifa tarifa = factoria.getTarifa(cliente.getTarifa(), p);
                        getModelo().setTarifa(cliente, tarifa);
                        return null;
                    }
            );
        }

        vistaNext(vista);
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaTarfias;
    }
}
