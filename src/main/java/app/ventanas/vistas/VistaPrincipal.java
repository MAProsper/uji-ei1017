package app.ventanas.vistas;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.acciones.AccionPrincipal;
import app.ventanas.controladores.ControladorPrincipal;
import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;

import static helpers.estaticos.Arguments.validate;

public class VistaPrincipal extends VistaPropia {
    public VistaPrincipal() {
        super(
                "Menu principal",
                "Bienvenido a gestor de clientes AkiCode",
                Table.empty(), Textbox.empty(), AccionPrincipal.values());
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorPrincipal);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorPrincipal();
    }
}
