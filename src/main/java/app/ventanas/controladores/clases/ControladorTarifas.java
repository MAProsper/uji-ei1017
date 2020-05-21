package app.ventanas.controladores.clases;

import app.componentes.Accion;
import app.componentes.acciones.AccionTarifas;
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
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser de este controlador", accion instanceof AccionTarifas);
        Vista vista = null;

        if (accion != AccionTarifas.VOLVER) {
            final VistaTarfias vistaActual = (VistaTarfias) getVista();
            final String precio = vistaActual.getTextbox(TextboxTarifas.PRECIO);
            final Cliente cliente = vistaActual.getCliente();

            final FactoryTarifas factoria = (FactoryTarifas) accion;
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
