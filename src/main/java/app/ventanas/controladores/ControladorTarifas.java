package app.ventanas.controladores;

import app.helpers.Parser;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;
import app.ventanas.acciones.AccionTarifas;
import app.ventanas.interfaces.Accion;
import app.ventanas.textboxes.TextboxTarifas;
import app.ventanas.vistas.VistaError;
import app.ventanas.vistas.VistaTarfias;
import clientes.Cliente;
import helpers.interfaces.FactoryTarifas;

import static helpers.estaticos.Arguments.validate;

public class ControladorTarifas extends Controlador {
    public ControladorTarifas() {
        super();
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionTarifas);
        Vista vista = null;

        if (accion != AccionTarifas.VOLVER) {
            final VistaTarfias vistaTarfias = (VistaTarfias) getVista();
            final String precio = vistaTarfias.getTextbox(TextboxTarifas.PRECIO);
            final Cliente cliente = vistaTarfias.getCliente();

            final FactoryTarifas factoria = (FactoryTarifas) accion;
            vista = VistaError.attempt(
                    () -> Parser.real("Precio", precio),
                    p -> {
                        cliente.setTarifa(factoria.getTarifa(cliente.getTarifa(), p));
                        getModelo().udpateVista();
                        return null;
                    }
            );
        }

        showNext(vista);
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaTarfias);
    }
}
